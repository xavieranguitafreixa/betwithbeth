package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.BettingHouse;
import com.factorialsigma.betwithbeth.model.Cuota;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.MarketType.MarketSide;
import com.factorialsigma.betwithbeth.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
public interface CuotaRepository extends JpaRepository<Cuota, Integer> {

    @Query("select c from Cuota c where c.one <= ?1 or c.x <= ?1 or c.two <= ?1")
    List<Cuota> findByOneLessThanOrXLessThanOrTwoLessThan(float value);

    Cuota findByMarketAndBettingHouse(Market market, BettingHouse bettingHouse);

    List<Cuota> findByXLessThan(float v);
}
