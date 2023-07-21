package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.ExportPictureDto;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entity.Pictures;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private static final String PATH_PICTURE = "src/main/resources/files/pictures.json";

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files
                .readString(Path.of(PATH_PICTURE));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                        .fromJson(readFromFileContent(), PictureSeedDto[].class))
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto);

                    Optional<Pictures> byPath = this.pictureRepository
                            .findByPath(pictureSeedDto.getPath());
                    if (byPath.isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid
                            ?
                            String.format("Successfully imported Picture, with size %.2f",
                                    pictureSeedDto.getSize())
                            :
                            "Invalid Picture"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(pictureSeedDto -> mapper.map(pictureSeedDto, Pictures.class))
                .forEach(pictureRepository::save);
        return sb.toString();
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();
        List<Pictures> pictureOver = this.pictureRepository.findAll();
        List<Pictures> forPrint = new ArrayList<>();
        for (Pictures current : pictureOver) {
            if (current.getSize() > 30000) {
                forPrint.add(current);
            }
        }
        forPrint.sort(new Comparator<Pictures>() {
            @Override
            public int compare(Pictures o1, Pictures o2) {
                return o1.getSize().compareTo(o2.getSize());
            }
        });
        for (Pictures currentPicture : forPrint) {
            sb.append(String.format("%.2f - %s", currentPicture.getSize(), currentPicture.getPath()));
            sb.append(System.lineSeparator()
            );
        }

        return sb.toString();

    }
}
