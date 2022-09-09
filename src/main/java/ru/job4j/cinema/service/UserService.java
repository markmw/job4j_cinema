package ru.job4j.cinema.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.UserDbStore;

import java.util.List;
import java.util.Optional;

@Service @ThreadSafe
public class UserService {
    private final UserDbStore store;

    public UserService(UserDbStore store) {
        this.store = store;
    }

    public Optional<User> add(User user) {
        return store.addUser(user);
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    public boolean update(User user) {
        return store.updateUser(user);
    }

    public List<User> findAll() {
        return store.findAll();
    }

    public Optional<User> findUserByEmailAndPhone(String email, String phone) {
        return store.findUserByEmailAndPhone(email, phone);
    }
}
