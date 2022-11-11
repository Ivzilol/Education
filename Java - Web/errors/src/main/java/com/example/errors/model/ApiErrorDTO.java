package com.example.errors.model;

public class ApiErrorDTO {

    private final Long objectId;
    private final String massage;

    public ApiErrorDTO(Long objectId, String massage) {
        this.objectId = objectId;
        this.massage = massage;
    }

    public Long getObjectId() {
        return objectId;
    }

    public String getMassage() {
        return massage;
    }
}
