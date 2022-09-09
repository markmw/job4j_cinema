package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketDbStore;

import java.util.List;
import java.util.Optional;

@Service @ThreadSafe
public class TicketService {
    private final TicketDbStore ticketStore;

    public TicketService(TicketDbStore store) {
        this.ticketStore = store;
    }

    public Optional<Ticket> add(Ticket ticket) {
        return ticketStore.add(ticket);
    }

    public Optional<Ticket> findById(int id) {
        return ticketStore.findById(id);
    }

    public boolean update(Ticket ticket) {
        return ticketStore.update(ticket);
    }

    public List<Ticket> findAll() {
        return ticketStore.findAll();
    }
}
