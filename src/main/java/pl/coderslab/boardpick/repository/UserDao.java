package pl.coderslab.boardpick.repository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import pl.coderslab.boardpick.entity.CurrentUser;
import pl.coderslab.boardpick.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveUser(User entity) {
        entityManager.persist(entity);
    }

    public void update(User entity) {
        entityManager.merge(entity);
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public void delete(long id) {
        User entity = findById(id);
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }


    public Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = (CurrentUser)authentication.getPrincipal();
        Long userId = currentUser.getUserId();
        return userId;
    }

  /*public List<User> findAll() {
      Query query = entityManager.createQuery("SELECT a FROM User a");
      return query.getResultList();

  }*/


}
