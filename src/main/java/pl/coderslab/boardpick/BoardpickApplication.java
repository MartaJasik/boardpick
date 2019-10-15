package pl.coderslab.boardpick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.boardpick.entity.Role;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BoardpickApplication {

    public static void main(String[] args) {

        SpringApplication.run(BoardpickApplication.class, args);


    }
}


