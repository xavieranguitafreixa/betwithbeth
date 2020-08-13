package com.factorialsigma.betwithbeth.wanabet;

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
public class WanabetScheduler {

    @Autowired
    private WanabetScraper wanabetScraper;

//    @Scheduled(fixedDelay = 300_000) //FIXME todos los empates salen a cuota 2.0
    public void hourlyTask() {
        wanabetScraper.soccer1X2Scraper(null);
    }
}
