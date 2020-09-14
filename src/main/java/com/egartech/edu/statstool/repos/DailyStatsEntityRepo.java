package com.egartech.edu.statstool.repos;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyStatsEntityRepo extends JpaRepository<DailyStatsEntity, Integer> {
}
