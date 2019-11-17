package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.Play;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.*;
import static java.util.stream.Collectors.*;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/gamestats")
public class GameStatsController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayRepository playRepository;


    @GetMapping("/{id}")
    public String gameStats(@PathVariable Long id, Model model) {
        Game game = gameRepository.findById(id).orElse(new Game());
        model.addAttribute("game", game);
        model.addAttribute("playscount", gamePlaysCount(game));

        List<Play> playsByGame = playRepository.findAllByGame(game);
        List<User> winnersByGame = new ArrayList<>();

        for (Play play : playsByGame) {
            winnersByGame.add(play.getWinner());
        }

        model.addAttribute("plays", playsByGame);

        Map<User, Long> counts =
                winnersByGame.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Map<User, Long> sortedCounts = counts
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(3)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        model.addAttribute("wins", sortedCounts);

        return "gamestats";
    }


    public Integer gamePlaysCount(Game game) {
        Integer count = playRepository.countPlaysByGame(game);
        return count;
    }

}






