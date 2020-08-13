package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.BettingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author
 * @version 1.0
 */
public interface BettingHouseRepository extends JpaRepository<BettingHouse, Integer> {

    BettingHouse findByName(String name);
}
