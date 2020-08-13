package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.Sport;
import com.factorialsigma.betwithbeth.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findByName(String name);

    List<Team> findBySportOrderByName(Sport sport);
}
