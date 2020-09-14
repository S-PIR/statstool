package com.egartech.edu.statstool.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString(of = {"id", "instrumentName"})
@Entity
@Table(name = "fin_instrument")
public class FinInstrument {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "instrument_name")
    private String instrumentName;

    public FinInstrument(String instrumentName) {
        this.instrumentName = instrumentName;
    }
}
