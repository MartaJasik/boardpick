package pl.coderslab.boardpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;
import java.util.List;
import java.util.Set;


public interface GameRepository extends JpaRepository<Game, Long> {

 Set<Game> findByUsersContains(User user);
 List<Game> findFirst12ByOrderByAddedToDbDesc();



}
