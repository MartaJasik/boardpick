package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.coderslab.boardpick.XMLscrapper.Scrapper.*;

@Controller
@RequestMapping("/picker")
public class PickerController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String collection() {
        return "form/pickgame";
    }


    @PostMapping
    public String pickGame(@RequestParam String category, @RequestParam String players, @RequestParam String weight, @RequestParam String time, Model model) {
        if (category.equals("mine")) {
            Set<Game> myGames = getMyGames();
            Integer intPlayers = Integer.parseInt(players);
            final Integer weightMin;
            final Integer weightMax;
            switch (weight) {
                case "easy":
                    weightMin = 1;
                    weightMax = 2;
                    break;
                case "easymedium":
                    weightMin = 1;
                    weightMax = 3;
                    break;
                case "medium":
                    weightMin = 2;
                    weightMax = 3;
                    break;
                case "mediumhard":
                    weightMin = 2;
                    weightMax = 5;
                    break;
                case "hard":
                    weightMin = 3;
                    weightMax = 5;
                    break;
                case "dontcare":
                    weightMin = 1;
                    weightMax = 5;
                    break;
                default:
                    weightMin = 1;
                    weightMax = 5;
            }

            final Integer timeMin;
            final Integer timeMax;
            switch (time) {
                case "quick":
                    timeMin = 0;
                    timeMax = 30;
                    break;
                case "quickstandard":
                    timeMin = 0;
                    timeMax = 60;
                    break;
                case "standard":
                    timeMin = 30;
                    timeMax = 60;
                    break;
                case "long":
                    timeMin = 60;
                    timeMax = 180;
                    break;
                case "dontcare":
                    timeMin = 0;
                    timeMax = 180;
                    break;
                default:
                    timeMin = 0;
                    timeMax = 180;
            }

            Set<Game> foundMyGames = myGames.stream()
                    .filter(p -> p.getMinPlayers() <= intPlayers)
                    .filter(p -> p.getMaxPlayers() >= intPlayers)
                    .filter(p -> p.getMinPlaytime() <= timeMin || p.getMinPlaytime() <= timeMax)
                    .filter(p -> p.getMaxPlaytime() >= timeMax || p.getMinPlaytime() >= timeMin)
                    .filter(p -> p.getWeight() >= weightMin)
                    .filter(p -> p.getWeight() <= weightMax)
                    .collect(Collectors.toSet());

            model.addAttribute("games", foundMyGames);

        } else if (category.equals("new")){
            List<String> tenFound = new ArrayList<>();


            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (!(auth instanceof AnonymousAuthenticationToken)) {
                List<String> allFound = advancedFinder(players, weight, time, 15);
                List<String> userGameId = new ArrayList<>();
                Long userId = userDao.currentUserId();
                Set<Game> set = gameRepository.findByUsersContains(userRepository.findById(userId).orElse(new User()));
                for (Game game:set) {
                    userGameId.add(new Long(game.getId()).toString());
                }
                for(String string:allFound) {
                    if (!userGameId.contains(string)) {
                        tenFound.add(string);
                    }
                }

            } else {
                tenFound = advancedFinder(players, weight, time, 10);
            }
            List<Game> gamesFound = new ArrayList<>();
            for (String id : tenFound) {
                gamesFound.add(getGameLong(id));
            }
            model.addAttribute("games", gamesFound);
        }
        return "form/pickgame";
    }


    public Set<Game> getMyGames() {
        Long currentUserId = userDao.currentUserId();
        User user = userRepository.findById(currentUserId).orElse(new User());
        return gameRepository.findByUsersContains(user);
    }



}

