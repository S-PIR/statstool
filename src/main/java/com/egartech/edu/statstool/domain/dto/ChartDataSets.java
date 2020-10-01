package com.egartech.edu.statstool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartDataSets {
    private String label;
    private String backgroundColor;
    private String borderColor;
    private Double[] data;

}
