package com.factorialsigma.betwithbeth.bwin;

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
public class BWinScheduler {

    @Autowired
    private BWinScraper bWinScraper;

    @Scheduled(fixedDelay = 300_000)
    public void hourlyTask() {
        bWinScraper.soccer1X2Scraper(null);
    }
}
