package com.factorialsigma.betwithbeth.wanabet;

import com.factorialsigma.betwithbeth.Scraper;
import com.factorialsigma.betwithbeth.model.BettingHouse;
import com.factorialsigma.betwithbeth.model.Event;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.repository.*;
import com.factorialsigma.betwithbeth.wanabet.model.Betoffer;
import com.factorialsigma.betwithbeth.wanabet.model.BetofferResponse;
import com.factorialsigma.betwithbeth.wanabet.model.Outcome;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author
 * @version 1.0
 */
@Service
@Slf4j
public class WanabetScraper extends Scraper {

    private static final String NAME = "Wanabet";
    private static final String HOST = "https://www.wanabet.es";
    private static final String ICON_URL = "http://www.trebol-apuestas.com/wp-content/uploads/2016/07/twitter-wanabet-trebol-apuestas.png";

    private static final String finalUrl = "https://api.kambi.com/offering/api/v2/rfes/betoffer/main/group/1000095049.json?id=1000095050&id=1000095001&id=1000094985&id=1000094994&id=1000094991&id=1000093652&id=2000096421&id=1000093656&id=1000093657&type=2&lang=ES_es";

    private static final String TODAY_SOCCER_URL = HOST + "/widgets/dashboard/app/#!/";

    private final WanabetFeignClient wanabetFeignClient;

    @Autowired
    public WanabetScraper(MarketRepository marketRepository, EventRepository eventRepository,
                          BettingHouseRepository bettingHouseRepository, RunnerRepository runnerRepository,
                          CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                          TeamRepository teamRepository, WanabetFeignClient wanabetFeignClient) {
        super(marketRepository, eventRepository, bettingHouseRepository, runnerRepository, cuotaRepository,
                cuotaHistoricaRepository, teamRepository, NAME, HOST, ICON_URL, log);
        this.wanabetFeignClient = wanabetFeignClient;
    }

    @Transactional
    @Override
    public void soccer1X2Scraper(String url) {
        BettingHouse bettingHouse = getBettingHouse();
        BetofferResponse offersResponse = wanabetFeignClient.getOffers();

        List<com.factorialsigma.betwithbeth.wanabet.model.Event> entradas = offersResponse.getEvents();
        log.info("Número de entradas en la página " + finalUrl + " " + entradas.size());
        for (com.factorialsigma.betwithbeth.wanabet.model.Event eventx : entradas) {
            try {
                String titulo = eventx.getName().replace(" - ", " V ").trim().toUpperCase();
                String homeTeam = eventx.getHomeName().trim().toUpperCase();
                String visitingTeam = eventx.getAwayName().trim().toUpperCase();
                Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(eventx.getStart());

                Event event = getEvent(titulo, homeTeam, visitingTeam, start);

                Market market = getMarket(event, !eventx.getState().equals("NOT_STARTED"), null, null); //TODO el estado

                Betoffer betofferToRemove = null;
                for (Betoffer betoffer : offersResponse.getBetoffers()) {
                    if (Objects.equals(betoffer.getEventId(), eventx.getId())) {
                        betofferToRemove = betoffer;
                        if (betoffer.getBetOfferType().getName().equalsIgnoreCase("1X2")) {
                            String x12_1 = null;
                            String x12_X = null;
                            String x12_2 = null;
                            for (Outcome outcome : betoffer.getOutcomes()) {
                                if (outcome.getOddsFractional().contains("/")) {
                                    String[] split = outcome.getOddsFractional().split("/");
                                    float val = Integer.parseInt(split[0]) / Integer.parseInt(split[1]);
                                    switch (outcome.getLabel()) {
                                        case "1":
                                            x12_1 = String.valueOf(val);
                                            break;
                                        case "X":
                                            x12_X = String.valueOf(val);
                                            break;
                                        case "2":
                                            x12_2 = String.valueOf(val);
                                            break;
                                    }
                                }
                            }
                            if (x12_1 != null || x12_X != null || x12_2 != null) {
                                moveLastCuotas(bettingHouse, market);
                                createCuota(bettingHouse, market, x12_1, x12_X, x12_2, HOST);
                            }
                        }
                    }
                }
                if (betofferToRemove != null) {
                    offersResponse.getBetoffers().remove(betofferToRemove);
                }

            } catch (Exception ignored) {
                log.warn(ignored.getLocalizedMessage(), ignored);
            }
        }
    }

    /*@Transactional
    @Override
    public void soccer1X2Scraper(String url) {
        String finalUrl = TODAY_SOCCER_URL;
        if (url != null) {
            finalUrl = url;
        }

        BettingHouse bettingHouse = getBettingHouse();

        Document doc = getHtmlDocument(finalUrl);

        Elements entradas = doc.select("div.fila_apuesta");
        log.debug("Número de entradas en la página " + finalUrl + " " + entradas.size());

        log.debug(entradas.html());

        for (Element elem : entradas) {

            Elements names = elem.getElementsByClass("equipo");
            String homeTeam = names.get(0).text().trim();
            String visitingTeam = names.get(1).text().trim();
            String titulo = homeTeam + " V " + visitingTeam;
            String link = elem.getElementsByClass("a_mercado").attr("href");


            Event event = getEvent(titulo, homeTeam, visitingTeam);

            Market market = getMarket(event, false, null, null); //TODO en esta URL está algo live?

            Elements prices = elem.getElementsByClass("cuota");
            String x12_1 = prices.get(0).text().trim();
            String x12_X = prices.get(1).text().trim();
            String x12_2 = prices.get(2).text().trim();

            moveLastCuotas(bettingHouse, market);

            createCuota(bettingHouse, market, x12_1, x12_X, x12_2, link);
        }
    }*/


}
