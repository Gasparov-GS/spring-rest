package ru.gasparov.service;



import ru.gasparov.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);
    void removeUser(int id);
    List<User> allUser();
    Optional<User> findUserById(int id);
    Optional<User> findUserByName(String username);

}
