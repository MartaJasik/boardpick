package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.CurrentUser;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.GameDao;
import pl.coderslab.boardpick.repository.GameRepository;
import pl.coderslab.boardpick.repository.UserDao;
import pl.coderslab.boardpick.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.coderslab.boardpick.XMLscrapper.Scrapper.*;

@Controller
@RequestMapping("/")
public class CollectionController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/collection")
    public String collection() {
        return "collection";
    }

    @GetMapping("/collection/find/{id}")
    @ResponseBody
    public String find(@PathVariable Long id) {
        return "Znaleziona książka: " + gameDao.findById(id).toString();
    }

    @GetMapping("/collection/delete/{id}")
    public String delete(@PathVariable Long id) {
        gameDao.delete(id);

        return "redirect:/collection";
    }

    @GetMapping("/collection/addgame")
    public String addGameForm() {
        return "form/addgame";
    }


    @PostMapping("/collection/addgame")
    public String saveGameForm(@RequestParam String title, Model model) {
        List<String> tenFound = findFirstTen(title);
        List<Game> gamesFound = new ArrayList<>();
        for (String id : tenFound) {
            gamesFound.add(getGameShort(id));
        }
        model.addAttribute("games", gamesFound);
        return "form/addgame";
    }


    @GetMapping("/collection/add/{id}")
    public String add(@PathVariable String id) {
        Long userId = userDao.currentUserId();
        final Game game = getGameLong(id);
        Set<User> users = new HashSet<>();
        users.add(userDao.findById(userId));
        game.setUsers(users);
        gameDao.saveGame(game);

        return "redirect:/collection";
    }

    @ModelAttribute("mygames")
    public Set<Game> getMyGames() {
       // return gameDao.findAll();
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        return gameRepository.findByUsersContains(user);
    }

}






