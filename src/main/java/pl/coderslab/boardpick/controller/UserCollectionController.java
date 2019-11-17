package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.*;


@Controller
@RequestMapping("/")
public class UserCollectionController {


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/userCollection/{id}")
    public String userCollection(@PathVariable Long id, Model model) {
        Long currentId;
        try {
            currentId = userDao.currentUserId();
        } catch (ClassCastException e) {
            User user = userRepository.findFirstById(id);
            model.addAttribute("user", user);
            model.addAttribute("userGames", gameRepository.findByUsersContains(user));
            return "userCollection";
        }
        if (currentId == id) {
            return "redirect:/collection";
        }
        User user = userRepository.findFirstById(id);
        model.addAttribute("user", user);
        model.addAttribute("userGames", gameRepository.findByUsersContains(user));
        return "userCollection";

    }
}






