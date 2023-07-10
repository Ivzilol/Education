package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureSeedRootDto;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private static final String PICTURE_PATH = "src/main/resources/files/xml/pictures.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files
                .readString(Path.of(PICTURE_PATH));
    }

    @Override
    public String importPictures() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(PICTURE_PATH, PictureSeedRootDto.class)
                .getPictureSeedDtoList()
                .stream()
                .filter(pictureSeedDto -> {
                    boolean isValid = validationUtil.isValid(pictureSeedDto);

                    if (pictureSeedDto.getUrl() == null) {
                        isValid = false;
                    }

                    sb.append(isValid
                            ?
                            String.format("Successfully imported picture - %s",
                                    pictureSeedDto.getUrl())
                            :
                            "Invalid Picture"
                    ).append(System.lineSeparator());
                    return isValid;
                })
                .map(pictureSeedDto -> mapper.map(pictureSeedDto, Picture.class))
                .forEach(this.pictureRepository::save);
        return sb.toString();
    }

}
