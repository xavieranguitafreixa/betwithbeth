package com.factorialsigma.betwithbeth.williamhill;

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
public class WHScraper extends Scraper {

    private static final String NAME = "WilliamHill";
    private static final String HOST = "http://sports.williamhill.es/bet_esp/es/betting";
    private static final String ICON_URL = "https://apuestasonline.net/wp-content/uploads/2016/08/app_williamhill_icono.png";

    private static final String TODAY_SOCCER_URL = HOST + "/y/5/tm/Fútbol.html";

    @Autowired
    public WHScraper(MarketRepository marketRepository, EventRepository eventRepository,
                     BettingHouseRepository bettingHouseRepository, RunnerRepository runnerRepository,
                     CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                     TeamRepository teamRepository) {
        super(marketRepository, eventRepository, bettingHouseRepository, runnerRepository, cuotaRepository,
                cuotaHistoricaRepository, teamRepository, NAME, HOST, ICON_URL, log);
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

        Elements entradas = doc.select("tr.rowOdd");
        log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());

        for (Element elem : entradas) {
            try {
                Elements prices = elem.getElementsByClass("eventprice");
                String x12_1 = prices.get(0).text().trim();
                String x12_X = prices.get(1).text().trim();
                String x12_2 = prices.get(2).text().trim();

                Elements names = elem.getElementsByClass("leftPad");
                String titulo = names.get(2).text().trim().toUpperCase().replace("\u00A0", "");
                String link = "http" + names.get(2).child(0).attr("href").split("http")[1];

                String homeTeam = titulo.split("₋")[0].trim();
                String visitingTeam = titulo.split("₋")[1].trim();
                titulo = homeTeam + " V " + visitingTeam;

                Event event = getEvent(titulo, homeTeam, visitingTeam);

                Market market = getMarket(event, false, null, null);

                moveLastCuotas(bettingHouse, market);

                createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
            } catch (Exception e) {
                log.warn(e.getLocalizedMessage(), e);
            }
        }
    }

}
