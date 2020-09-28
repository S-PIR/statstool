package com.egartech.edu.statstool.service;

import com.egartech.edu.statstool.domain.DailyStatsEntity;
import com.egartech.edu.statstool.domain.FinInstrument;
import com.egartech.edu.statstool.domain.dto.CharDataDto;
import com.egartech.edu.statstool.domain.dto.DataSets;
import com.egartech.edu.statstool.repos.FinInstrumentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public CharDataDto getInstrumentStats(FinInstrument finInstrument) {
        Set<DailyStatsEntity> dailyStatsSet = finInstrument.getDailyStatsEntities();
        if (dailyStatsSet.isEmpty()) {
            return CharDataDto.EMPTY_CHAR_DATA;
        }
        TreeMap<LocalDate, BigDecimal> stats =  dailyStatsSet.stream()
                .collect(Collectors.toMap(
                        DailyStatsEntity::getStatusDate,
                        DailyStatsEntity::getPrice,
                        (o1, o2) -> o1,
                        TreeMap::new)
                );

        String label = finInstrument.getInstrument().instrumentName;
        String backgroundTransparent = "rgba(255, 99, 132, 0.2)";
        String borderColorRed = "#FF0000";
        Double[] data = stats.values().stream()
                .map(BigDecimal::doubleValue)
                .toArray(Double[]::new);
        DataSets[] dataSets = new DataSets[]{new DataSets(label, backgroundTransparent, borderColorRed, data)};

        LocalDate[] labels = stats.keySet().toArray(LocalDate[]::new);
        CharDataDto charData = new CharDataDto(labels, dataSets);

        return charData;
    }
}
