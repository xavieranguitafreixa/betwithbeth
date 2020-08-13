package com.factorialsigma.betwithbeth.marathonbet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author
 * @version 1.0
 */
@Configuration
@Slf4j
public class MarathonBetScheduler {

    @Autowired
    private MarathonBetScraper marathonBetScraper;

    @Scheduled(fixedDelay = 300_000)
    public void hourlyTask() {
        marathonBetScraper.soccer1X2Scraper(null);
    }
}
