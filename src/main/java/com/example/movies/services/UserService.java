package com.example.movies.services;

import com.example.movies.models.Role;
import com.example.movies.models.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addRoleToUser(String username, String role);
    public User getUser(String username);
    public Role getRole(String roleName);
    public List<User> getUsers();
}
