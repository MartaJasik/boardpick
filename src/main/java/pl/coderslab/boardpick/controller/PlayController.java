package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.Play;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.coderslab.boardpick.XMLscrapper.Scrapper.*;

@Controller
@RequestMapping("/plays")
public class PlayController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayRepository playRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String plays(Model model) {
        model.addAttribute("play", new Play());
        return "form/addplay";
    }


    @PostMapping
    public String savePlayForm(@ModelAttribute Play play) {
        playRepository.save(play);
        return "redirect:/plays";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        playRepository.delete(playRepository.findFirstById(id));

        return "redirect:/plays";
    }


    @ModelAttribute("mygames")
    public Set<Game> getMyGames() {
        // return gameDao.findAll();
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        return gameRepository.findByUsersContains(user);
    }

    @ModelAttribute("myplays")
    public Set<Play> getMyPlays() {
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        return playRepository.findByPlayersContains(user);
    }

    @ModelAttribute("wincount")
    public Integer myWinCount() {
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        Set<Play> mySet = playRepository.findByWinner(user);
        return mySet.size();
    }

    @ModelAttribute("playscount")
    public Integer myPlaysCount() {
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        Set<Play> mySet = playRepository.findByPlayersContains(user);
        return mySet.size();
    }


    @ModelAttribute("count")
    public Integer howManyIOwn() {
        Long currentUserId = userDao.currentUserId();
        User user = userDao.findById(currentUserId);
        Set<Game> mySet = gameRepository.findByUsersContains(user);
        return mySet.size();
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}






