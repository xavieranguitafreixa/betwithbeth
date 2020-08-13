package com.factorialsigma.betwithbeth.controller;

import com.factorialsigma.betwithbeth.model.Cuota;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.Sport;
import com.factorialsigma.betwithbeth.model.repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @version 1.0
 */
@Controller
public class HomeController {

    @Autowired
    private CuotaRepository cuotaRepository;

    @RequestMapping(path = {"/tenis"})
    public String tenis(Model model) {
        //TODO sacar a un service, generificar
        List<Cuota> empatesHot = cuotaRepository.findAll();
        Set<Market> markets = empatesHot.stream()
                .map(Cuota::getMarket)
                .filter(market -> market.getEvent().getSport().equals(Sport.TENIS))
//                .filter(market -> market.getCuotas().size() > 1) //TODO
                .collect(Collectors.toSet());
        List<Market> markkts = parseMarkets(markets);
        model.addAttribute("markets", markkts);
        model.addAttribute("tab", "tenisNav");
        model.addAttribute("titulo", "Cuotas de tenis");
        model.addAttribute("subTitulo", "(se marcan cuotas más altas y surebets)");
        return "home";
    }

    @RequestMapping(path = {"/futbolHot"})
    public String futbolHot(Model model) {
        //TODO sacar a un service, generificar
        List<Cuota> empatesHot = cuotaRepository.findByXLessThan(2.5F);
        Set<Market> markets = empatesHot.stream()
                .map(Cuota::getMarket)
                .filter(market -> market.getEvent().getSport().equals(Sport.FUTBOL))
                .collect(Collectors.toSet());
        List<Market> markkts = parseMarkets(markets);
        model.addAttribute("markets", markkts);
        model.addAttribute("tab", "futbolNav");
        model.addAttribute("titulo", "Cuotas hot de fútbol");
        model.addAttribute("subTitulo", "(se marcan cuotas más altas y surebets)");
        return "home";
    }

    @RequestMapping(path = {"/futbol"})
    public String futbol(Model model) {
        //TODO sacar a un service, generificar
        List<Cuota> empatesHot = cuotaRepository.findAll();
        Set<Market> markets = empatesHot.stream()
                .map(Cuota::getMarket)
                .filter(market -> market.getEvent().getSport().equals(Sport.FUTBOL))
                .filter(market -> market.getCuotas().size() > 1)
                .collect(Collectors.toSet());
        List<Market> markkts = parseMarkets(markets);
        model.addAttribute("markets", markkts);
        model.addAttribute("tab", "futbolNav");
        model.addAttribute("titulo", "Cuotas de fútbol");
        model.addAttribute("subTitulo", "(cuotas en más de una casa, se marcan cuotas más altas y surebets)");
        return "home";
    }

    @RequestMapping(path = {"/", "home", "/home"})
    public String home(Model model) {
        //TODO sacar a un service, generificar
        List<Cuota> empatesHot = cuotaRepository.findAll();
        Set<Market> markets = empatesHot.stream().map(Cuota::getMarket).filter(market -> market.getCuotas().size() > 1).collect(Collectors.toSet());
        List<Market> markkts = parseMarkets(markets);
        model.addAttribute("markets", markkts);
        model.addAttribute("tab", "homeNav");
        model.addAttribute("titulo", "Cuotas comparables");
        model.addAttribute("subTitulo", "(cuotas en más de una casa, se marcan cuotas más altas y surebets)");
        return "home";
    }

    protected List<Market> parseMarkets(Set<Market> markets) {
        for (Market market : markets) {
            float max1 = 0F;
            float maxX = 0F;
            float max2 = 0F;
            for (Cuota cuota : market.getCuotas()) {
                if (cuota.getOne() > max1) {
                    max1 = cuota.getOne();
                }
                if (cuota.getX() > maxX) {
                    maxX = cuota.getX();
                }
                if (cuota.getTwo() > max2) {
                    max2 = cuota.getTwo();
                }
            }

            market.set_maxBet(new Cuota());
            market.get_maxBet().setOne(max1);
            market.get_maxBet().setX(maxX);
            market.get_maxBet().setTwo(max2);
            float index= getIndex(market, market.get_maxBet());
            market.get_maxBet().set_index(index);

            if (index < 1) { //Surebet
                market.set_sureBet(market.get_maxBet());
            }
        }
        List<Market> markkts = new ArrayList<>(markets);
        Collections.sort(markkts, (m1, m2) -> {
            if (m1.get_sureBet() != null && m2.get_sureBet() == null) {
                return -1;
            }
            if (m2.get_sureBet() != null && m1.get_sureBet() == null) {
                return 1;
            }
            if (m1.get_sureBet() != null && m2.get_sureBet() != null) {
                Float v1 = getIndex(m1, m1.get_sureBet());
                Float v2 = getIndex(m2, m2.get_sureBet());
                return v1.compareTo(v2); //menor a mayor
            }
            Float v1 = getIndex(m1, m1.get_maxBet());
            Float v2 = getIndex(m2, m2.get_maxBet());
            return v1.compareTo(v2); //menor a mayor
        });
        return markkts;
    }

    private float getIndex(Market market, Cuota cuota) {
        float index;
        if (market.getEvent().getSport().equals(Sport.TENIS)) {
            index = 1 / cuota.getOne() + 1 / cuota.getTwo();
        } else {
            index = 1 / cuota.getOne() + 1 / cuota.getX() + 1 / cuota.getTwo();
        }
        return index;
    }
}
