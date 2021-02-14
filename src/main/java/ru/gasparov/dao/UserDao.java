package ru.gasparov.dao;

import ru.gasparov.model.Role;
import ru.gasparov.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    void removeRole(Role role);
    void removeUser(int id);
    List<User> getAllUsers();
    Optional<User> findUserById(int id);
    Optional<User> findUserByName(String username);
}
