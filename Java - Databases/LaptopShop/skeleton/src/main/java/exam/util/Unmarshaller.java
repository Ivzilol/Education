package exam.util;

import javax.xml.bind.JAXBException;

public interface Unmarshaller {

    <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException;
}
