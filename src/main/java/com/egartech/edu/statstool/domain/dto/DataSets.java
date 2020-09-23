package com.egartech.edu.statstool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataSets {
    private String label;
    private String backgroundColor;
    private Double[] data;

}
