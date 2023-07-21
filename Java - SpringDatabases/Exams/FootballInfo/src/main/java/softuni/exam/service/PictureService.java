package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PictureService {
    String importPictures() throws JAXBException;

    boolean areImported();

    String readPicturesXmlFile() throws IOException;
}
