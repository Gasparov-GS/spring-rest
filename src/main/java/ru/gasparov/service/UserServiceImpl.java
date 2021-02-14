package ru.gasparov.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gasparov.dao.UserDao;
import ru.gasparov.model.User;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> allUser() {
        return userDao.getAllUsers();
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public Optional<User> findUserByName(String username) {
        return userDao.findUserByName(username);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findUserByName(s).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
