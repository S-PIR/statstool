package com.egartech.edu.statstool.domain.dto;

import com.egartech.edu.statstool.domain.FinInstrument;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
public class DailyStatsEntityDto {
    private Integer id;
    private Date statusDate;
    private FinInstrument instrument;
    private BigDecimal price;
}
