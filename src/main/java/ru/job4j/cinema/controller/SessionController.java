package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import ru.job4j.cinema.service.SessionService;

@Controller @ThreadSafe
public class SessionController {
    private final SessionService store;

    public SessionController(SessionService store) {
        this.store = store;
    }
}
