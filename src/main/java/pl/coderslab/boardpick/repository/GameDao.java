package pl.coderslab.boardpick.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.coderslab.boardpick.entity.Game;
import pl.coderslab.boardpick.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class GameDao {

    @Autowired
    UserDao userDao;

    @PersistenceContext
    EntityManager entityManager;

    //public void saveGame(Game entity) {
    //    entityManager.persist(entity);
   // }

    public void saveGame(Game entity) {
    //jeśli gra istnieje w bazie - dodać tylko usera do listy. jeśli nei istnieje - dodac cala gre i usera do listy
        if (findById(entity.getId()) == null) {
            entityManager.persist(entity);
        } else {
            Set<User> users = entity.getUsers();
            User user = userDao.findById(userDao.currentUserId());
            users.add(user);
            update(entity);
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
        User user = userDao.findById(userDao.currentUserId());
        users.remove(user);
          /*      entity.setUsers(users);
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));*/
    }

  public List<Game> findAll() {
    Query query = entityManager.createQuery("SELECT a FROM Game a");
    //  Long currentUserId = userDao.currentUserId();


      return query.getResultList();

  }




}
