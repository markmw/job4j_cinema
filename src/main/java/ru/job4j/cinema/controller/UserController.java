package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller @ThreadSafe
public class UserController {
    private final UserService userService;

    public UserController(UserService store) {
        this.userService = store;
    }

    @GetMapping("/registrationForm")
    public String registrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, Model model) {
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("fail", true);
            model.addAttribute("message", "Пользователь с такой почтой или номером телефона уже существует!");
            return "registration";
        }
        return "login";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("mustLoginForTakeTicket") != null) {
            model.addAttribute("fail", true);
            model.addAttribute("message", "Для приобретения билета необходимо войти в свою учетную запись!");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession httpSession) {
        Optional<User> userDb = userService.findUserByEmailAndPhone(user.getEmail(), user.getPhone());
        if (userDb.isEmpty()) {
            model.addAttribute("fail", true);
            model.addAttribute("message", "Неверный номер телефона или почта!");
            return "login";
        }
        httpSession.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/loginPage";
    }
}
