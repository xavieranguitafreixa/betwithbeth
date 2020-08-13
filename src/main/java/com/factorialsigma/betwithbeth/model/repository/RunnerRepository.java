package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.MarketType.MarketSide;
import com.factorialsigma.betwithbeth.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
public interface RunnerRepository extends JpaRepository<Runner, Integer> {

//    List<Runner> findByValueLessThanAndSideEq(float value, MarketSide side);
}
