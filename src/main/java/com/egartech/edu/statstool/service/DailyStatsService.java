package com.egartech.edu.statstool.service;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import com.egartech.edu.statstool.repos.DailyStatsEntityRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyStatsService {
    private final DailyStatsEntityRepo dailyStatsEntityRepo;

    public DailyStatsService(DailyStatsEntityRepo dailyStatsEntityRepo) {
        this.dailyStatsEntityRepo = dailyStatsEntityRepo;
    }

    public List<DailyStatsEntity> findAll() {
        return dailyStatsEntityRepo.findAll();
    }

    public DailyStatsEntity create(DailyStatsEntity dailyStatsEntity) {
        return dailyStatsEntityRepo.save(dailyStatsEntity);
    }

    public DailyStatsEntity update(DailyStatsEntity source, DailyStatsEntity target) {
        BeanUtils.copyProperties(source, target, "id");
        return dailyStatsEntityRepo.save(target);
    }

    public void delete(DailyStatsEntity dailyStatsEntity) {
        dailyStatsEntityRepo.delete(dailyStatsEntity);
    }

}
