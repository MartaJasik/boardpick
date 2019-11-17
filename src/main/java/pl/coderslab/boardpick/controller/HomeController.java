package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.GameRepository;
import pl.coderslab.boardpick.repository.UserRepository;
import pl.coderslab.boardpick.service.UserService;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String hello() {
        return "index";
    }


    @GetMapping("/picker")
    public String pick() {
        return "form/pickgame";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {

        return "form/login";
    }

    @ModelAttribute("recentGames")
    public List<Game> getRecentGames() {
        return gameRepository.findFirst12ByOrderByAddedToDbDesc();
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }


}