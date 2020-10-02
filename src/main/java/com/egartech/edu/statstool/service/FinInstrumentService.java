package com.egartech.edu.statstool.service;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import com.egartech.edu.statstool.domain.FinInstrument;
import com.egartech.edu.statstool.domain.dto.ChartDataDto;
import com.egartech.edu.statstool.domain.dto.ChartDataSets;
import com.egartech.edu.statstool.repos.FinInstrumentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class FinInstrumentService {
    private final FinInstrumentRepo instrumentRepo;

    @Autowired
    public FinInstrumentService(FinInstrumentRepo instrumentRepo) {
        this.instrumentRepo = instrumentRepo;
    }

    public List<FinInstrument> findAll() {
        return instrumentRepo.findAll();
    }

    public FinInstrument create(FinInstrument dailyStatsEntity) {
        return instrumentRepo.save(dailyStatsEntity);
    }

    public FinInstrument update(FinInstrument source, FinInstrument target) {
        BeanUtils.copyProperties(source, target, "id");
        return instrumentRepo.save(target);
    }

    public void delete(FinInstrument finInstrument) {
        instrumentRepo.delete(finInstrument);
    }

    public ChartDataDto getChartData(FinInstrument finInstrument) {
        Set<DailyStatsEntity> dailyStatsSet = finInstrument.getDailyStatsEntities();
        if (dailyStatsSet.isEmpty()) {
            return ChartDataDto.EMPTY_CHAR_DATA;
        }
        TreeMap<LocalDate, BigDecimal> stats =  dailyStatsSet.stream()
                .collect(Collectors.toMap(
                        DailyStatsEntity::getStatusDate,
                        DailyStatsEntity::getPrice,
                        (o1, o2) -> o1,
                        TreeMap::new)
                );

        String label = finInstrument.getInstrument().instrumentName;
        String background = "rgba(255, 99, 132, 0.2)";
        String borderColorRed = "#FF0000";
        Double[] data = stats.values().stream()
                .map(BigDecimal::doubleValue)
                .toArray(Double[]::new);
        ChartDataSets[] dataSets = new ChartDataSets[]{new ChartDataSets(label, background, borderColorRed, data)};

        LocalDate[] labels = stats.keySet().toArray(LocalDate[]::new);
        ChartDataDto charData = new ChartDataDto(labels, dataSets);

        return charData;
    }

}
