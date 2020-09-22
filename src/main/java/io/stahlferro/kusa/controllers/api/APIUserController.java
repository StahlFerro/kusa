package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.mappers.UserMapper;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class APIUserController {

    private final UserService userService;

    @Autowired
    private UserMapper userMapper;

    public APIUserController(UserService userService) { this.userService = userService; }

    @GetMapping("/all")
    private List<UserBase> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserBase getUserById(@PathVariable("id") long id) {
        UserBase userBase = userService.getUserByIdOrError(id);
        return userBase;
    }

    @GetMapping("/{id}/description")
    public String getUserStringByid(@PathVariable("id") long id) {
        UserBase userBase = userService.getUserByIdOrError(id);
        return userBase.toString();
    }

    @GetMapping()
    public List<UserBase> getUsersByName(@RequestParam("name") String name) {
        return userService.getUsersByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserBase userBase) {
        userService.add(userBase);
        return new ResponseEntity<>(userBase, HttpStatus.CREATED);
    }

    @GetMapping("/nextid")
    public long getNextId() { return userService.getNextId(); }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateUser(@PathVariable("id") long id, @RequestBody UserBaseDto userBaseDto) {
        UserBase userBase = userService.getUserByIdOrError(id);
        log.info(String.format("User: %s\nUserDto: %s", userBase.toString(), userBaseDto.toString()));
        userService.update(userBase, userBaseDto);
        return ResponseEntity.ok(userBase.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        UserBase userBase = userService.getUserByIdOrError(id);
        String deletionMsg = userService.delete(userBase);
        return deletionMsg;
    }
}
