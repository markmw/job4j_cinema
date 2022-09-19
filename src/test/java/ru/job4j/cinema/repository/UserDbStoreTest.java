package ru.job4j.cinema.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDbStoreTest {
    private static BasicDataSource pool;

    @BeforeAll
    public static void loadPool() {
        pool = new TestConnectionPool();
    }

    @AfterEach
    public void clean() throws SQLException {
        try (PreparedStatement statement = pool.getConnection()
                .prepareStatement("DELETE FROM users")) {
            statement.execute();
        }
    }

    @Test
    public void whenAddUser() {
        UserDbStore store = new UserDbStore(pool);
        User user = new User("Ivan", "123@mail", "2-00-00");
        store.addUser(user);
        User userFromDb = store.findById(user.getId()).get();
        assertThat(userFromDb.getUsername()).isEqualTo(user.getUsername());
        assertThat(userFromDb.getEmail()).isEqualTo(user.getEmail());
        assertThat(userFromDb.getPhone()).isEqualTo(user.getPhone());
    }
}