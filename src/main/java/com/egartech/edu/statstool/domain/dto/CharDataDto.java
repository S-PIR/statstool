package com.egartech.edu.statstool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CharDataDto {

    public static final CharDataDto EMPTY_CHAR_DATA = new CharDataDto(
            new LocalDate[0],
            new DataSets[0]
    );

    private LocalDate[] labels;
    private DataSets[] datasets;
}
