package pl.coderslab.boardpick.service;

import pl.coderslab.boardpick.entity.Role;
import pl.coderslab.boardpick.entity.User;

import java.util.Arrays;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
    
}