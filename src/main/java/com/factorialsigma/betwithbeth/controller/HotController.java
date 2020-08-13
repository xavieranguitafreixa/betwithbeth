package com.factorialsigma.betwithbeth.controller;

import com.factorialsigma.betwithbeth.model.Cuota;
import com.factorialsigma.betwithbeth.model.Market;
import com.factorialsigma.betwithbeth.model.repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author
 * @version 1.0
 */
@Controller
public class HotController {

    @Autowired
    private CuotaRepository cuotaRepository;

    @RequestMapping(path = {"/hot", "hot"})
    public String home(Model model) {
        //TODO sacar a un service, generificar
        List<Cuota> empatesHot = cuotaRepository.findByXLessThan(3F);
        Set<Market> marketsHot = empatesHot.stream().map(Cuota::getMarket).filter(market -> market.getCuotas().size() >= 1).collect(Collectors.toSet());
        model.addAttribute("empatesHot", marketsHot);
        return "home";
    }
}
