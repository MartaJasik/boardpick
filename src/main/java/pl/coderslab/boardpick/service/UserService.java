package pl.coderslab.boardpick.service;

import pl.coderslab.boardpick.entity.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
    
}