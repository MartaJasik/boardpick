package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.CurrentUser;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.GameDao;
import pl.coderslab.boardpick.repository.GameRepository;
import pl.coderslab.boardpick.repository.UserDao;
import pl.coderslab.boardpick.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserDao userDao;


    @Autowired
    UserService userService;

    @GetMapping("/")
    public String hello() {
        return "index";
    }


    @GetMapping("/picker")
    public String pick() {
        return "picker";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
       //User user = new User("admin", "pass");

       //userService.saveUser(user);

        return "admin/login";
    }


}