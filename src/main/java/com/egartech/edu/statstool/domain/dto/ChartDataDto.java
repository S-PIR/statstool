package com.egartech.edu.statstool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ChartDataDto {

    public static final ChartDataDto EMPTY_CHAR_DATA = new ChartDataDto(
            new LocalDate[0],
            new ChartDataSets[0]
    );

    private LocalDate[] labels;
    private ChartDataSets[] datasets;
}
