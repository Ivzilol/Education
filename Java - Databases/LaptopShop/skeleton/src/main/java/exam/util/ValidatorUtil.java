package exam.util;

import org.springframework.stereotype.Component;

import javax.xml.validation.Validator;


public interface ValidatorUtil  {

    <E>boolean isValid(E entity);

}
