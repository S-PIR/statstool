package com.egartech.edu.statstool.repos;

import com.egartech.edu.statstool.domain.FinInstrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinInstrumentRepo extends JpaRepository<FinInstrument, Integer> {
}
