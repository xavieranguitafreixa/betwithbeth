package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.BettingHouse;
import com.factorialsigma.betwithbeth.model.Cuota;
import com.factorialsigma.betwithbeth.model.CuotaHistorica;
import com.factorialsigma.betwithbeth.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
public interface CuotaHistoricaRepository extends JpaRepository<CuotaHistorica, Integer> {

    List<CuotaHistorica> findByMarketAndBettingHouse(Market market, BettingHouse bettingHouse);

//    @Modifying
//    @Query(value = "insert into CUOTA_HISTORICA (select * from CUOTA c where c.id = ?1)", nativeQuery = true)
//    void save(int cuotaId);
}
