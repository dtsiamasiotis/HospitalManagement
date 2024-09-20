package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AdmissionStatusConverter implements AttributeConverter<AdmissionStatus, String> {
    @Override
    public String convertToDatabaseColumn(AdmissionStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public AdmissionStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        return Stream.of(AdmissionStatus.values())
                .filter(c -> c.name().equals(s))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}