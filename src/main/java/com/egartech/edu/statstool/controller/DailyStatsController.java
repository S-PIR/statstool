package com.egartech.edu.statstool.controller;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import com.egartech.edu.statstool.domain.Views;
import com.egartech.edu.statstool.service.DailyStatsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class DailyStatsController {

    private final DailyStatsService dailyStatsService;

    public DailyStatsController(DailyStatsService dailyStatsService) {
        this.dailyStatsService = dailyStatsService;
    }

    @GetMapping
    @JsonView(Views.FulInfo.class)
    public List<DailyStatsEntity> list() {
        return dailyStatsService.findAll();
    }

    @GetMapping("{id}")
    public DailyStatsEntity getOne(@PathVariable("id") DailyStatsEntity dailyStatsEntity) {
        return dailyStatsEntity;
    }

    @PostMapping
    public DailyStatsEntity create(@RequestBody DailyStatsEntity dailyStatsEntity) {
        return dailyStatsService.create(dailyStatsEntity);
    }

    @PutMapping("{id}")
    public DailyStatsEntity update(
            @PathVariable("id") DailyStatsEntity target,
            @RequestBody DailyStatsEntity source
            ){
        return dailyStatsService.update(source, target);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") DailyStatsEntity dailyStatsEntity) {
        dailyStatsService.delete(dailyStatsEntity);
    }
}
