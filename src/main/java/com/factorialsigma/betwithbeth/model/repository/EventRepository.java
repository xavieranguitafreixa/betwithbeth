package com.factorialsigma.betwithbeth.model.repository;

import com.factorialsigma.betwithbeth.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author
 * @version 1.0
 */
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByFullName(String fullName);
}
