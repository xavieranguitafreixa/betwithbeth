package com.factorialsigma.betwithbeth;

import com.factorialsigma.betwithbeth.model.*;
import com.factorialsigma.betwithbeth.model.repository.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * @author
 * @version 1.0
 */
public abstract class Scraper {

    private final EventRepository eventRepository;
    private final BettingHouseRepository bettingHouseRepository;
    private final RunnerRepository runnerRepository;
    private final CuotaRepository cuotaRepository;
    private final CuotaHistoricaRepository cuotaHistoricaRepository;
    private final MarketRepository marketRepository;
    private final TeamRepository teamRepository;

    private final String NAME;
    private final String HOST;
    private final String ICON_URL;
    private final Logger log;

    public Scraper(MarketRepository marketRepository, EventRepository eventRepository,
                   BettingHouseRepository bettingHouseRepository, RunnerRepository runnerRepository,
                   CuotaRepository cuotaRepository, CuotaHistoricaRepository cuotaHistoricaRepository,
                   TeamRepository teamRepository,
                   String name, String host, String iconUrl,
                   Logger log) {
        this.marketRepository = marketRepository;
        this.eventRepository = eventRepository;
        this.bettingHouseRepository = bettingHouseRepository;
        this.runnerRepository = runnerRepository;
        this.cuotaRepository = cuotaRepository;
        this.cuotaHistoricaRepository = cuotaHistoricaRepository;
        this.teamRepository = teamRepository;
        this.NAME = name;
        this.HOST = host;
        this.ICON_URL = iconUrl;
        this.log = log;
    }

    public abstract void soccer1X2Scraper(String url);

    protected BettingHouse getBettingHouse() {
        BettingHouse bettingHouse = bettingHouseRepository.findByName(NAME);
        if (bettingHouse == null) {
            log.info("Creating new Betting House " + NAME);
            bettingHouse = new BettingHouse();
            bettingHouse.setUrl(HOST);
            bettingHouse.setName(NAME);
            bettingHouse.setIconUrl(ICON_URL);
            bettingHouseRepository.save(bettingHouse);
        }
        return bettingHouse;
    }

    protected Event getEvent(String titulo, String homeTeam, String visitingTeam) {
        return getEvent(titulo, homeTeam, visitingTeam, null, null);
    }

    protected Event getEvent(String titulo, String homeTeam, String visitingTeam, Sport sport) {
        return getEvent(titulo, homeTeam, visitingTeam, sport, null);
    }

    protected Event getEvent(String titulo, String homeTeam, String visitingTeam, Date startDate) {
        return getEvent(titulo, homeTeam, visitingTeam, null, startDate);
    }

    protected Event getEvent(String titulo, String homeTeam, String visitingTeam, Sport sport, Date startDate) {
        Event event = eventRepository.findByFullName(sinAcentos(titulo));
        if (event == null) {
            log.info("Creating new event " + sinAcentos(titulo));
            event = new Event();
            event.setFullName(sinAcentos(titulo));
            event.setHomeTeam(getTeam(homeTeam, sport));
            event.setVisitingTeam(getTeam(visitingTeam, sport));
            event.setCreationDate(new Date());
            event.setStartDate(startDate);
            event.setSport(sport != null ? sport : Sport.FUTBOL);
            eventRepository.save(event);
        } else {
            log.debug("Getting event " + sinAcentos(titulo));
        }
        return event;
    }

    protected Team getTeam(String name, Sport sport) {
        name = sinAcentos(name.trim().toUpperCase());
        if (sport == null) {
            sport = Sport.FUTBOL;
        }
        Team team = teamRepository.findByName(name);
        if (team == null) {
            log.info("Creating new " + sport.name() + " team " + name);
            team = new Team();
            team.setName(name);
            team.setSport(sport);
            teamRepository.save(team);
        }
        return team;
    }

    private static String sinAcentos(String string) {
        return string
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("Ü", "U")
                ;
    }


    protected Market getMarket(Event event, Boolean inPlay, Integer homeScore, Integer visitorScore) {
        Market market = null;
        for (Market aux : event.getMarkets()) {
            if (aux.getMarketType().equals(MarketType.X12)) {
                market = aux;
                break;
            }
        }
        if (market == null) {
            log.debug("Creating new market 1X2 for event " + event.getFullName());
            market = new Market();
            market.setEvent(event);
            market.setMarketType(MarketType.X12);
        } else {
            log.debug("Getting 1X2 market for event " + event.getFullName());
        }
        market.setLastUpdate(new Date());
        market.setCurrentInPlay(inPlay);
        market.setCurrentHomeScore(homeScore);
        market.setCurrentVisitorScore(visitorScore);
        if (homeScore != null && visitorScore != null) {
            market.setCurrentResult(homeScore > visitorScore ? MarketType.MarketSide.ONE : Objects.equals(homeScore, visitorScore) ? MarketType.MarketSide.X : MarketType.MarketSide.TWO);
        }
        marketRepository.save(market);
        return market;
    }

    protected void moveLastCuotas(BettingHouse bettingHouse, Market market) {
        log.debug("Moviendo cuotas antiguas de " + market.getEvent().getFullName());
        Cuota lastCuota = cuotaRepository.findByMarketAndBettingHouse(market, bettingHouse);
        if (lastCuota != null) {
            cuotaHistoricaRepository.save(CuotaHistorica.builder()
                    .id(lastCuota.getId())
                    .bettingHouse(lastCuota.getBettingHouse())
                    .market(lastCuota.getMarket())
                    .date(lastCuota.getDate())
                    .one(lastCuota.getOne())
                    .x(lastCuota.getX())
                    .two(lastCuota.getTwo())
                    .url(lastCuota.getUrl())
                    .build());
            cuotaRepository.delete(lastCuota);
        }
    }

    protected void createCuota(BettingHouse bettingHouse, Market market, String x12_1, String x12_X, String x12_2, String link) {
        log.debug("Creando cuota para " + market.getEvent().getFullName());
        Cuota cuota = new Cuota();
        cuota.setMarket(market);
        cuota.setBettingHouse(bettingHouse);
        cuota.setDate(new Date());
        cuota.setOne(getNumOrZero(x12_1));
        cuota.setX(getNumOrZero(x12_X));
        cuota.setTwo(getNumOrZero(x12_2));
        cuota.setUrl((!link.contains("http") ? HOST : "") + link);
        cuotaRepository.save(cuota);
    }


    protected static Float getNumOrZero(String numString) {
        try {
            return Float.valueOf(numString);
        } catch (Exception ignored) {
            return 0F;
        }
    }

    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
            "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0",
            "Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0",
            "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0",

            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36",
    };

    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     *
     * @param url la url
     * @return Documento con el HTML
     */
    protected Document getHtmlDocument(String url) {

        Document doc = null;
        try {
            int bodySize = 10 * 1024 * 1024; //10MB
            doc = Jsoup.connect(url).userAgent(randomFrom(USER_AGENTS)).timeout(100000).maxBodySize(bodySize).get();
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage(), ex);
        }
        return doc;
    }

    private static <T> T randomFrom(T... items) {
        return items[new Random().nextInt(items.length)];
    }
}
