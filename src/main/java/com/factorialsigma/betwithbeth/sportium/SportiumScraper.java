package com.factorialsigma.betwithbeth.sportium;

import com.factorialsigma.betwithbeth.Scraper;
import com.factorialsigma.betwithbeth.model.*;
import com.factorialsigma.betwithbeth.model.MarketType.MarketSide;
import com.factorialsigma.betwithbeth.model.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * @author
 * @version 1.0
 */
@Service
@Slf4j
public class SportiumScraper extends Scraper {

    private static final String NAME = "Sportium";
    private static final String HOST = "http://sports.sportium.es";
    private static final String ICON_URL = "http://www.elchediario.com/imagenes/sisnews/original/news108807.jpg";

    private static final String TODAY_SOCCER_URL = HOST + "/es/ProximasApuestas";
//    private static final String TODAY_SOCCER_URL = HOST + "/es/overview";

    @Autowired
    public SportiumScraper(MarketRepository marketRepository, EventRepository eventRepository, BettingHouseRepository bettingHouseRepository,
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

        Elements entradas = doc.select("tr.mkt_content");
        log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());

        for (Element elem : entradas) {
            try {
                Elements names = elem.getElementsByClass("seln-name");
                String homeTeam = names.get(0).text().trim().toUpperCase();
                String visitingTeam = names.get(1).text().trim().toUpperCase();

                String titulo = homeTeam + " V " + visitingTeam;

                String link = elem.getElementsByClass("mkt-count").get(0).child(0).attr("href");

                Event event = getEvent(titulo, homeTeam, visitingTeam);

                Market market = getMarket(event, false, null, null); //TODO buscar la URL the in live o hacer foreach de las url

                Elements prices = elem.getElementsByClass("dec");
                String x12_1 = prices.get(0).text();
                String x12_X = prices.get(1).text();
                String x12_2 = prices.get(2).text();

                moveLastCuotas(bettingHouse, market);

                createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
            } catch (Exception e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
    }


}
