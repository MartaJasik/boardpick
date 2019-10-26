package pl.coderslab.boardpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.Play;
import pl.coderslab.boardpick.entity.User;

import java.util.List;
import java.util.Set;

public interface PlayRepository extends JpaRepository<Play, Long> {

    Set<Play> findByPlayersContains(User user);
    Set<Play> findByWinner(User user);
    Play findFirstById(long id);
    Integer countPlaysByGame(Game game);
    List<Play> findAllByGame(Game game);



}
