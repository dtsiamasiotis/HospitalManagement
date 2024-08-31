package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class VisitStatusConverter implements AttributeConverter<VisitStatus, String> {
    @Override
    public String convertToDatabaseColumn(VisitStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public VisitStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return Stream.of(VisitStatus.values())
                .filter(c -> c.name().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}