package com.egartech.edu.statstool.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_stats")
@Getter
@Setter
@NoArgsConstructor
public class DailyStatsEntity implements Serializable {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Integer id;

    @Column(name = "status_date", nullable = false)
    @JsonView(Views.FulInfo.class)
    private LocalDate statusDate;

    @ManyToOne
    @JoinColumn(name = "instrument_id", nullable = false)
    @JsonView(Views.FulInfo.class)
    private FinInstrument finInstrument;


    @Column(name = "price", nullable = false)
    @JsonView(Views.FulInfo.class)
    private BigDecimal price;

}
