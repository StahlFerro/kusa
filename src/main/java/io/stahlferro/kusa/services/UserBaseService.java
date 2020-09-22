package io.stahlferro.kusa.services;

import io.stahlferro.kusa.mappers.UserBaseMapper;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.repositories.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserBaseService {
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    public UserBaseRepository repository;
    @Autowired
    public UserBaseMapper mapper;

    public List<UserBase> getAll() {
        return repository.findAll();
    }

    public Optional<UserBase> getUserById(UUID id) {
        return repository.findById(id);
    }
    public UserBase getUserByIdOrError(UUID id) {
        UserBase userBase = getUserById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid KeyCard Id: " + id));
        return userBase;
    }
    public List<UserBase> getUsersByName(String name) { return repository.findByName(name); }

//    public UUID getNextId() { return repository.getNextId(); }

    public UserBase getUserByLoginName(String loginName) {
        UserBase user = repository.findByLoginName(loginName);
        return user;
    }

//    public UserBase add(UserBase userBase) {
//        repository.save(userBase);
//        return userBase;
//    }

    public UserBase create(UserBaseDto dto) {
        UserBase user = mapper.createUserFromDto(dto);
        return repository.save(user);
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
