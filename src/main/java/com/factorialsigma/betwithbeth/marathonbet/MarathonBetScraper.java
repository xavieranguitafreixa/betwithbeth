package com.factorialsigma.betwithbeth.marathonbet;

import com.factorialsigma.betwithbeth.Scraper;
import com.factorialsigma.betwithbeth.model.BettingHouse;
import com.factorialsigma.betwithbeth.model.Event;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 */
@Service
@Slf4j
public class MarathonBetScraper extends Scraper {

    private static final String NAME = "Marathon Bet";
    private static final String HOST = "https://www.mbet.es";
    private static final String ICON_URL = "https://pbs.twimg.com/profile_images/766295456636870656/RPreb9_5.jpg";

    private static final String EVENT_URL = "/es/events.htm?id=";

    private static final String TODAY_SOCCER_URL = HOST + "/es/betting/Football/?menu=11";

    @Autowired
    public MarathonBetScraper(MarketRepository marketRepository, EventRepository eventRepository, BettingHouseRepository bettingHouseRepository,
                              RunnerRepository runnerRepository, CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                              TeamRepository teamRepository) {
        super(marketRepository, eventRepository, bettingHouseRepository, runnerRepository, cuotaRepository, cuotaHistoricaRepository, teamRepository,
                NAME, HOST, ICON_URL, log);
    }

    @Transactional
    @Override
    public void soccer1X2Scraper(String url) {
        String finalUrl = TODAY_SOCCER_URL;
        if (url != null) {
            finalUrl = url;
        }

        BettingHouse bettingHouse = getBettingHouse();

        Document doc = getHtmlDocument(finalUrl);

        Elements entradas = doc.select("tbody[data-event-name*=vs]");
        log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());

        for (Element elem : entradas) {
            try {
                String titulo = elem.attr("data-event-name").trim().replace(" vs ", " V ").toUpperCase();
                String homeTeam = titulo.split(" V ")[0];
                String visitingTeam = titulo.split(" V ")[1];

                String link = HOST + EVENT_URL + elem.attr("data-event-treeid").trim();

                Event event = getEvent(titulo, homeTeam, visitingTeam);

                Market market = getMarket(event, false, null, null); //TODO en esta URL está algo live?

                Elements prices = elem.getElementsByClass("selection-link");
                if (prices.size() > 0) {
                    String x12_1 = prices.get(0).text();
                    String x12_X = prices.get(1).text();
                    String x12_2 = prices.get(2).text();

                    moveLastCuotas(bettingHouse, market);

                    createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
                }
            } catch (Exception e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
    }


}
