package com.egartech.edu.statstool.controller;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import com.egartech.edu.statstool.domain.Views;
import com.egartech.edu.statstool.domain.dto.EventType;
import com.egartech.edu.statstool.domain.dto.ObjectType;
import com.egartech.edu.statstool.service.DailyStatsService;
import com.egartech.edu.statstool.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/stats")
public class DailyStatsController {

    private final DailyStatsService dailyStatsService;
    private final BiConsumer<EventType, DailyStatsEntity> wsSender;

    public DailyStatsController(DailyStatsService dailyStatsService, WsSender wsSender) {
        this.dailyStatsService = dailyStatsService;
        this.wsSender = wsSender.getSender(ObjectType.DAILY_STATS, Views.FulInfo.class);
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
        DailyStatsEntity createdDailyStatsEntity = dailyStatsService.create(dailyStatsEntity);
        wsSender.accept(EventType.CREATE, createdDailyStatsEntity);
        return createdDailyStatsEntity;
    }

    @PutMapping("{id}")
    public DailyStatsEntity update(
            @PathVariable("id") DailyStatsEntity target,
            @RequestBody DailyStatsEntity source
            ){
        DailyStatsEntity updatedDailyStatsEntity = dailyStatsService.update(source, target);
        wsSender.accept(EventType.UPDATE, updatedDailyStatsEntity);
        return updatedDailyStatsEntity;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") DailyStatsEntity dailyStatsEntity) {
        wsSender.accept(EventType.REMOVE, dailyStatsEntity);
        dailyStatsService.delete(dailyStatsEntity);
    }

}
