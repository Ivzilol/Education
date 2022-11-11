package com.example.errors.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private final Long objectID;

    public ObjectNotFoundException(Long ObjectID) {

        objectID = ObjectID;
    }

    public Long getObjectID() {
        return objectID;
    }
}
