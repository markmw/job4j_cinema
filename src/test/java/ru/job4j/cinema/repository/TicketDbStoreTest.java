package ru.job4j.cinema.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.Main;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class TicketDbStoreTest {
    private static BasicDataSource pool;

    @BeforeAll
    public static void loadPool() {
        pool = new Main().loadPool();
    }

    @AfterEach
    public void clean() throws SQLException {
        try (PreparedStatement st = pool.getConnection().prepareStatement(
                "DELETE FROM ticket")) {
            st.execute();
        }
        try (PreparedStatement st = pool.getConnection().prepareStatement(
                "DELETE FROM users")) {
            st.execute();
        }
        try (PreparedStatement st = pool.getConnection().prepareStatement(
                "DELETE FROM sessions")) {
            st.execute();
        }
    }

    @Test
    public void whenAddTicket() {
        TicketDbStore ticketStore = new TicketDbStore(pool);
        UserDbStore userStore = new UserDbStore(pool);
        SessionDbStore sessionStore = new SessionDbStore(pool);
        User user = new User("Ivan", "123@mail", "2-00-00");
        userStore.addUser(user);
        Session session = new Session("Taxi");
        sessionStore.addSession(session);
        Ticket ticket = new Ticket(session, user, 2, 2);
        ticketStore.add(ticket);
        Ticket ticketFromDB = ticketStore.findById(ticket.getId()).get();
        assertThat(ticketFromDB.getSession().getId()).isEqualTo(ticket.getSession().getId());
        assertThat(ticketFromDB.getUser().getId()).isEqualTo(ticket.getUser().getId());
        assertThat(ticketFromDB.getRow()).isEqualTo(ticket.getRow());
        assertThat(ticketFromDB.getCell()).isEqualTo(ticket.getCell());
    }
}