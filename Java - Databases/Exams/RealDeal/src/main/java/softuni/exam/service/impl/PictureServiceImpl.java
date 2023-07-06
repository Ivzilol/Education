package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureSeedDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private final CarRepository carRepository;

    private static final String PICTURE_PATH = "src/main/resources/files/json/pictures.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files
                .readString(Path.of(PICTURE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readPicturesFromFile(), PictureSeedDto[].class))
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto);

                    Optional<Picture> findByName = this.pictureRepository
                            .findByName(pictureSeedDto.getName());

                    if (findByName.isPresent()) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully import picture %s",
                                    pictureSeedDto.getName())
                            :
                            "Invalid picture"
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(pictureSeedDto -> {
                    Picture picture = mapper.map(pictureSeedDto, Picture.class);
                    Optional<Car> car = this.carRepository.findById(pictureSeedDto.getCar());
                    picture.setCar(car.get());
                    return picture;
                })
                .forEach(this.pictureRepository::save);
        return sb.toString();
    }
}
