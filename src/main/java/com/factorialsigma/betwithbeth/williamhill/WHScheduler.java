package com.factorialsigma.betwithbeth.williamhill;

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
public class WHScheduler {

    @Autowired
    private WHScraper whScraper;

    @Scheduled(fixedDelay = 300_000)
    public void hourlyTask() {
    	whScraper.soccer1X2Scraper(null);
    }
}
