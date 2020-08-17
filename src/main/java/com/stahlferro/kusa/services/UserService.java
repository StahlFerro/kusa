package com.stahlferro.kusa.services;

import com.stahlferro.kusa.mappers.KeyCardMapper;
import com.stahlferro.kusa.mappers.UserMapper;
import com.stahlferro.kusa.models.KeyCard;
import com.stahlferro.kusa.models.KeyCardDto;
import com.stahlferro.kusa.models.User;
import com.stahlferro.kusa.models.UserDto;
import com.stahlferro.kusa.repositories.KeyCardRepository;
import com.stahlferro.kusa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;
    @Autowired
    public UserMapper mapper;

    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return repository.findById(id);
    }
    public User getUserByIdOrError(long id) {
        User user = getUserById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        return user;
    }
    public List<User> getUsersByName(String name) { return repository.findByName(name); }

    public long getNextId() { return repository.getNextId(); }
    public User add(User user) {
        repository.save(user);
        return user;
    }

    public void update(User user, UserDto userDto) {
        mapper.updateUserFromDto(userDto, user);
        repository.save(user);
    }

    public String delete(User user) {
        String deletionMsg = String.format("Deleted %s", user.toString());
        repository.delete(user);
        return deletionMsg;
    }
}
