package com.factorialsigma.betwithbeth.betfair;

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
public class BetfairScraper extends Scraper {

    private static final String NAME = "Betfair";
    private static final String HOST = "https://www.betfair.es";
    private static final String ICON_URL = "http://a4.mzstatic.com/eu/r30/Purple62/v4/83/48/73/83487313-03c4-5c91-e11e-0f88cc6b1bda/icon175x175.png";

    private static final String TODAY_SOCCER_URL = HOST + "/sport/football?action=loadCompetition&modules=multipickavbId@1075&selectedTabType=TODAY";

    @Autowired
    public BetfairScraper(MarketRepository marketRepository, EventRepository eventRepository,
                          BettingHouseRepository bettingHouseRepository, RunnerRepository runnerRepository,
                          CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                          TeamRepository teamRepository) {
        super(marketRepository, eventRepository, bettingHouseRepository, runnerRepository, cuotaRepository, cuotaHistoricaRepository, teamRepository,
                NAME, HOST, ICON_URL, log);
    }

    @Transactional
    public void soccer1X2Scraper(String url) {
        String finalUrl = TODAY_SOCCER_URL;
        if (url != null) {
            finalUrl = url;
        }

        BettingHouse bettingHouse = getBettingHouse();

        Document doc = getHtmlDocument(finalUrl);

        Elements entradas = doc.select("li.com-coupon-line");
        log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());

        for (Element elem : entradas) {
            try {
                String titulo = elem.getElementsByAttribute("data-event").attr("data-event").trim().toUpperCase();

                String resultado = elem.getElementsByClass("ui-score-home").text().trim() + "-" + elem.getElementsByClass("ui-score-away").text().trim();

                String homeTeam = elem.getElementsByClass("home-team-name").text().trim();
                String visitingTeam = elem.getElementsByClass("away-team-name").text().trim();

                String link = elem.getElementsByClass("event-link").attr("href").trim();
                String eventId = link.split("=")[1];

                Event event = getEvent(titulo, homeTeam, visitingTeam);

                boolean inPlay = elem.getElementsByClass("ui-inplay").size() > 0;

                String x12_1 = "";
                String x12_X = "";
                String x12_2 = "";
                Elements markets = elem.getElementsByClass("market-1x2");
                for (Element aux1 : markets) {
                    for (Element aux2 : aux1.getElementsByClass("sel-0")) {
                        Elements aux3 = aux2.getElementsByClass("ui-runner-price");
                        for (Element element : aux3) {
                            x12_1 = element.text().trim();
                        }
                    }
                    for (Element aux2 : aux1.getElementsByClass("sel-1")) {
                        Elements aux3 = aux2.getElementsByClass("ui-runner-price");
                        for (Element element : aux3) {
                            x12_X = element.text().trim();
                        }
                    }
                    for (Element aux2 : aux1.getElementsByClass("sel-2")) {
                        Elements aux3 = aux2.getElementsByClass("ui-runner-price");
                        for (Element element : aux3) {
                            x12_2 = element.text().trim();
                        }
                    }
                }
                int homeScore = 0;
                int visitorScore = 0;
                if (resultado.length() > 1) {
                    homeScore = Integer.parseInt(resultado.split("-")[0].trim());
                    visitorScore = Integer.parseInt(resultado.split("-")[1].trim());
                }

                Market market = getMarket(event, inPlay, homeScore, visitorScore);

                moveLastCuotas(bettingHouse, market);

                createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
            } catch (NumberFormatException e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
    }
}
