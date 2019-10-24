package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.validation.LoginValidationGroup;

import javax.validation.Validator;

@RequestMapping("/register")
@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @GetMapping
    public String registerForm(Model model) {

        final User user = new User();
        model.addAttribute("user", new User());

        return "form/register";
    }


   /* public String registerUser(@Validated({LoginValidationGroup.class}) User user, BindingResult result) {
        if (result.hasErrors() && !isAuthorized(user)) {
            return "register";
        }
//dodawanie usera do sesji tutaj & (przy rejestracji usera -> szyfrowanie hasła

        return "redirect:/"; //redirect zmieni tez strone, nie tylko widok

    }*/
    @PostMapping
    private String registerUser(@Validated User user, BindingResult result) {
        if (result.hasErrors()) {
            return "form/register";
        }
        String email = user.getEmail();
        String password = user.getPassword();

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());

        user.setPassword(hashedPassword);

        final User userDb = userRepository.save(user);

        return "redirect:/form/login";

    }
}