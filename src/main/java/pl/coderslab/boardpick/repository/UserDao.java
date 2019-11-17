package pl.coderslab.boardpick.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.coderslab.boardpick.entity.CurrentUser;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public class UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;


    public Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((CurrentUser) authentication.getPrincipal()).getUserId();
        return userId;
    }

    public Integer howManyIOwn() {
        Long currentUserId = currentUserId();
        User user = userRepository.findById(currentUserId).orElse(new User());
        Set<Game> mySet = gameRepository.findByUsersContains(user);
        return mySet.size();
    }

}
