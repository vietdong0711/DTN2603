package com.example.buoi1.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ArticleStatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {// java -> sql
        if (status== null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String s) {// sql -> java
        if (s==null) {
            return null;
        }
        return Status.toEnum(s);
    }
}
