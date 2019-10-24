package pl.coderslab.boardpick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.boardpick.entity.Role;
import pl.coderslab.boardpick.entity.User;
import pl.coderslab.boardpick.repository.RoleRepository;
import pl.coderslab.boardpick.repository.UserRepository;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Validator validator;

    @GetMapping
    public String registerForm(Model model) {

        final User user = new User();
        model.addAttribute("user", new User());

        return "form/register";
    }


    @PostMapping
    private String registerUser(@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "form/register";
        }

        String username = user.getUsername();
        String password = user.getPassword();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        user.setPassword(hashedPassword);
        user.setEnabled(1);

            final User userDb = userRepository.save(user);

        return "redirect:/login";

    }
}