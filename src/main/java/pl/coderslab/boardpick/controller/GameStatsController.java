package pl.coderslab.boardpick.controller;

import org.jooq.lambda.Seq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.Play;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.coderslab.boardpick.XMLscrapper.Scrapper.advancedFinder;
import static pl.coderslab.boardpick.XMLscrapper.Scrapper.getGameLong;

@Controller
@RequestMapping("/gamestats")
public class GameStatsController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayRepository playRepository;


    @GetMapping("/{id}")
    public String gameStats(@PathVariable Long id, Model model) {
        Game game = gameDao.findById(id);
        model.addAttribute("game", game);
        model.addAttribute("playscount", gamePlaysCount(game));

        System.out.println(playRepository.countGameWinner());


        return "gamestats";
    }


    public Integer gamePlaysCount(Game game) {
        Integer count = playRepository.countPlaysByGame(game);
        return count;
    }

}






