package com.factorialsigma.betwithbeth.controller;

import com.factorialsigma.betwithbeth.image.ImageService;
import com.factorialsigma.betwithbeth.model.Sport;
import com.factorialsigma.betwithbeth.model.Team;
import com.factorialsigma.betwithbeth.model.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author
 * @version 1.0
 */
@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ImageService imageService;

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @RequestMapping(path = {"/futbol/teams"})
    public String teamsFutbol(Model model) {
        //TODO sacar a un service, generificar
        List<Team> teams = teamRepository.findBySportOrderByName(Sport.FUTBOL);
        //TODO start: sacar esto a cuando se crea el team
//        teams = teams.subList(0, 30);
        teams.stream().filter(team -> team.getImageUrl() == null || team.getImageUrl().length() < 7).forEach(team -> {
            String url = imageService.getImageUrl(team);
            team.setImageUrl(url);
            teamRepository.save(team);
        });
        //TODO end
        model.addAttribute("teams", teams);
        model.addAttribute("tab", "futbolNav");
        model.addAttribute("titulo", "Equipos de fútbol");
        model.addAttribute("subTitulo", "");
        model.addAttribute("ALPHABET", ALPHABET);
        return "team";
    }

    @RequestMapping(path = {"/tenis/teams"})
    public String teamsTenis(Model model) {
        //TODO sacar a un service, generificar
        List<Team> teams = teamRepository.findBySportOrderByName(Sport.TENIS);
        //TODO start: sacar esto a cuando se crea el team
        teams.stream().filter(team -> team.getImageUrl() == null).forEach(team -> {
            String url = imageService.getImageUrl(team);
            team.setImageUrl(url);
            teamRepository.save(team);
        });
        //TODO end
        model.addAttribute("teams", teams);
        model.addAttribute("tab", "tenisNav");
        model.addAttribute("titulo", "Jugadores de tenis");
        model.addAttribute("subTitulo", "");
        model.addAttribute("ALPHABET", ALPHABET);
        return "team";
    }

}
