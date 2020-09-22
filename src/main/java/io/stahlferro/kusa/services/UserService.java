package io.stahlferro.kusa.services;

import io.stahlferro.kusa.mappers.UserMapper;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public UserBaseRepository repository;
    @Autowired
    public UserMapper mapper;

    public List<UserBase> getAll() {
        return repository.findAll();
    }

    public Optional<UserBase> getUserById(long id) {
        return repository.findById(id);
    }
    public UserBase getUserByIdOrError(long id) {
        UserBase userBase = getUserById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        return userBase;
    }
    public List<UserBase> getUsersByName(String name) { return repository.findByName(name); }

    public long getNextId() { return repository.getNextId(); }
    public UserBase add(UserBase userBase) {
        repository.save(userBase);
        return userBase;
    }

    public void update(UserBase userBase, UserBaseDto userBaseDto) {
        mapper.updateUserFromDto(userBaseDto, userBase);
        repository.save(userBase);
    }

    public String delete(UserBase userBase) {
        String deletionMsg = String.format("Deleted %s", userBase.toString());
        repository.delete(userBase);
        return deletionMsg;
    }
}
