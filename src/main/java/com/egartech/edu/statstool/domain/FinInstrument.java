package com.egartech.edu.statstool.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "instrument"})
@Entity
@Table(name = "fin_instrument")
public class FinInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonView(Views.Id.class)
    private Integer id;

    @Column(name = "instrument_name", nullable = false)
    @JsonView(Views.FulInfo.class)
    private Instrument instrument;

    @OneToMany(mappedBy = "finInstrument", cascade = CascadeType.ALL)
    @JsonBackReference
    Set<DailyStatsEntity> dailyStatsEntities;

    public FinInstrument(String instrumentName) {
        this.instrument = Instrument.valueOf(instrumentName.toUpperCase());
        this.id = this.instrument.ordinal();
    }
}
