package com.egartech.edu.statstool.converter;

import com.egartech.edu.statstool.domain.Instrument;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class InstrumentConverter implements AttributeConverter<Instrument, String> {
    @Override
    public String convertToDatabaseColumn(Instrument instrument) {
        if (instrument == null) {
            System.out.println("from convertToDatabaseColumn(Instrument instrument)");
            return null;
        }
        return instrument.getInstrumentName();
    }

    @Override
    public Instrument convertToEntityAttribute(String instrumentName) {

        if (instrumentName == null) {
            System.out.println("from convertToEntityAttribute(String instrumentName)");
            return null;
        }

        return Stream.of(Instrument.values())
                .filter(c -> c.getInstrumentName().equals(instrumentName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
