package com.egartech.edu.statstool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum Instrument {
    ГАЗПРОМ("Газпром"),
    АВТОВАЗ("Автоваз"),
    СБЕРБАНК("Сбербанк");

    @JsonValue
    public final String instrumentName;

}
