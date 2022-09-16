package ru.job4j.cinema.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.Main;
import ru.job4j.cinema.model.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class SessionDbStoreTest {
    private static BasicDataSource pool;

    @BeforeAll
    public static void loadPool() {
        pool = new Main().loadPool();
    }

    @AfterEach
    public void clean() throws SQLException {
        try (PreparedStatement st = pool.getConnection()
                .prepareStatement("DELETE FROM sessions")) {
            st.execute();
        }
    }

    @Test
    public void whenAddSession() {
        SessionDbStore store = new SessionDbStore(pool);
        Session session = new Session("Белое солнце пустыни");
        store.addSession(session);
        Session sessionFromDB = store.findById(session.getId()).get();
        assertThat(sessionFromDB.getName()).isEqualTo(session.getName());
    }
}