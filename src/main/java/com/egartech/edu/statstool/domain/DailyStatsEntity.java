package com.egartech.edu.statstool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "daily_stats")
@Data
@NoArgsConstructor
public class DailyStatsEntity {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Integer id;

    @Column(name = "status_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonView(Views.FulInfo.class)
    private LocalDate statusDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "instrument_id", unique = true, nullable = false)
    @JsonView(Views.FulInfo.class)
    private FinInstrument finInstrument;

    @JoinColumn(name = "price")
    @JsonView(Views.FulInfo.class)
    private BigDecimal price;

}
