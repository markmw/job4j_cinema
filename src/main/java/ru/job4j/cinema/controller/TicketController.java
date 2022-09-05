package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import ru.job4j.cinema.service.TicketService;

@Controller @ThreadSafe
public class TicketController {
    private final TicketService store;

    public TicketController(TicketService store) {
        this.store = store;
    }
}
