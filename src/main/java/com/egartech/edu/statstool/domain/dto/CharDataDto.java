package com.egartech.edu.statstool.domain.dto;

import com.egartech.edu.statstool.domain.FinInstrument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CharDataDto {
    private LocalDate[] labels;
    private DataSets[] dataSets;
}
