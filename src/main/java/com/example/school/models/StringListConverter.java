package com.example.school.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(SEPARATOR, list) : "";}

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SEPARATOR)) : emptyList();
    }
}
