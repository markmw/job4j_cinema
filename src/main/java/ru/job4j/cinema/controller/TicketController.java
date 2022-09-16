package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.cinema.dto.PlaceDTO;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SessionService;
import ru.job4j.cinema.service.TicketService;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller @ThreadSafe
public class TicketController {
    private final SessionService sessionService;
    private final UserService userService;
    private final TicketService ticketService;

    public TicketController(SessionService sessionService, UserService userService,
                            TicketService ticketService) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("sessions", sessionService.findAll());
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUsername("Гость");
        }
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexWithPlaces/{filmId}")
    public String indexWithPlace(@PathVariable("filmId") int id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("sess_id", id);
        redirectAttributes.addFlashAttribute("freePlaces", sessionService.getFreePlaces(id));
        return "redirect:/index";
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "sess_id", required = false, defaultValue = "0") int sessionId,
                       HttpSession httpSession, Model model,
                       @RequestParam(value = "place", required = false) List<Integer> placesId) {
        User user = userService.findById(((User) httpSession.getAttribute("user")).getId()).get();
        Session session = sessionService.findById(sessionId).get();
        List<Ticket> tickets = new ArrayList<>();
        for (var el : placesId) {
            PlaceDTO placeDTO = sessionService.findPlaceById(el);
            tickets.add(new Ticket(
                    session,
                    user,
                    placeDTO.getRow(),
                    placeDTO.getCell()
            ));
        }
        model.addAttribute("fail_get_ticket", false);
        for (var ticket : tickets) {
            Optional<Ticket> optTicket = ticketService.add(ticket);
            if (optTicket.isEmpty()) {
                model.addAttribute("failTicket", ticket);
                model.addAttribute("fail_get_ticket", true);
            }
        }
        return "ticket_result";
    }
}
