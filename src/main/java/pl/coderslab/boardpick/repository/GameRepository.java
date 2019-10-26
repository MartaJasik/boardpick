package pl.coderslab.boardpick.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface GameRepository extends JpaRepository<Game, Long> {

 Set<Game> findByUsersContains(User user);
 List<Game> findFirst10ByOrderByAddedToDbDesc();



}
