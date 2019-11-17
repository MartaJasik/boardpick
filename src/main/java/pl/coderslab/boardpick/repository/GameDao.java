package pl.coderslab.boardpick.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class GameDao {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;


    public void saveGame(Game entity) {
        if (findById(entity.getId()) != null) {
            Set<User> users = findById(entity.getId()).getUsers();
            User user = userRepository.findById(userDao.currentUserId()).orElse(new User());
            users.add(user);
            entity.setUsers(users);
            update(entity);
        } else {
            entityManager.persist(entity);
        }
    }

    public void update(Game entity) {
        entityManager.merge(entity);
    }

    public Game findById(long id) {
        return entityManager.find(Game.class, id);
    }

    public void delete(long id) {
        Game entity = findById(id);
        Set<User> users = entity.getUsers();
        User user = userRepository.findById(userDao.currentUserId()).orElse(new User());
        users.remove(user);

    }


}
