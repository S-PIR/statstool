package com.egartech.edu.statstool.controller;

import com.egartech.edu.statstool.domain.FinInstrument;
import com.egartech.edu.statstool.domain.Views;
import com.egartech.edu.statstool.domain.dto.CharDataDto;
import com.egartech.edu.statstool.service.FinInstrumentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrument")
public class FinInstrumentController {

    private final FinInstrumentService instrumentService;

    public FinInstrumentController(FinInstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    @JsonView(Views.FulInfo.class)
    public List<FinInstrument> list() {
        return instrumentService.findAll();
    }

    @GetMapping("{id}")
    public FinInstrument getOne(@PathVariable("id") FinInstrument finInstrument) {
        return finInstrument;
    }

    @PostMapping
    public FinInstrument create(@RequestBody FinInstrument finInstrument) {
        return instrumentService.create(finInstrument);
    }

    @PutMapping("{id}")
    public FinInstrument update(
            @PathVariable("id") FinInstrument target,
            @RequestBody FinInstrument source
    ){
        return instrumentService.update(source, target);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") FinInstrument finInstrument) {
        instrumentService.delete(finInstrument);
    }

    @GetMapping("stats/{id}")
    public CharDataDto getInstrumentStats(@PathVariable("id") FinInstrument finInstrument){
        return instrumentService.getInstrumentStats(finInstrument);
    }
}
