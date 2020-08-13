package com.factorialsigma.betwithbeth.bwin;

import com.factorialsigma.betwithbeth.Scraper;
import com.factorialsigma.betwithbeth.model.BettingHouse;
import com.factorialsigma.betwithbeth.model.Event;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.Sport;
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
public class BWinScraper extends Scraper {

    private static final String NAME = "bwin";
    private static final String HOST = "https://sports.bwin.es";
    private static final String ICON_URL = "http://www.realzaragozapedia.es/wp-content/uploads/2016/04/nota-06.jpeg";

    private static final String[] URLS = {
            HOST + "/es/sports/4/apuestas/f%c3%batbol",
            HOST + "/es/sports/5/apuestas/tenis"
    };

    @Autowired
    public BWinScraper(MarketRepository marketRepository, EventRepository eventRepository, BettingHouseRepository bettingHouseRepository,
                       RunnerRepository runnerRepository, CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                       TeamRepository teamRepository) {
        super(marketRepository, eventRepository, bettingHouseRepository, runnerRepository, cuotaRepository, cuotaHistoricaRepository, teamRepository,
                NAME, HOST, ICON_URL, log);
    }

    @Transactional
    @Override
    public void soccer1X2Scraper(String url) {
        String[] finalUrls = URLS;
        if (url != null) {
            finalUrls = new String[]{url};
        }

        BettingHouse bettingHouse = getBettingHouse();

        for (String finalUrl : finalUrls) {
            Sport sport = finalUrl.contains("tenis") ? Sport.TENIS : Sport.FUTBOL;
            Document doc = getHtmlDocument(finalUrl);

            Elements entradas = doc.select("tr.mg-event-row");
            log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());

            for (Element elem : entradas) {
                try {
                    Element marketInfo = elem.getElementsByClass("mg-event-name-column").get(0).child(0).child(0);
                    String titulo = marketInfo.text().toUpperCase();
                    String homeTeam = titulo.split(" - ")[0];
                    String visitingTeam = titulo.split(" - ")[1];

                    if (homeTeam.contains("(")) {
                        homeTeam = homeTeam.split("\\(")[0];
                    }

                    if (visitingTeam.contains("(")) {
                        visitingTeam = visitingTeam.split("\\(")[0];
                    }

                    titulo = homeTeam + " V " + visitingTeam;

                    String link = marketInfo.attr("href");

                    Event event = getEvent(titulo, homeTeam, visitingTeam, sport);

                    Market market = getMarket(event, false, null, null); //TODO en esta URL está algo live?

                    Elements prices = elem.getElementsByClass("mg-option-button__option-odds");
                    String x12_1 = prices.get(0).text();
                    String x12_X = sport.equals(Sport.TENIS) ? null : prices.get(1).text();
                    String x12_2 = sport.equals(Sport.TENIS) ? prices.get(1).text() : prices.get(2).text();

                    moveLastCuotas(bettingHouse, market);

                    createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
                } catch (Exception e) {
                    log.warn(e.getLocalizedMessage(), e);
                }
            }
        }
    }
}
