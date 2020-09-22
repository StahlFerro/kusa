package io.stahlferro.kusa.controllers.api;

import io.stahlferro.kusa.mappers.UserBaseMapper;
import io.stahlferro.kusa.models.UserBase;
import io.stahlferro.kusa.models.UserBaseDto;
import io.stahlferro.kusa.services.UserBaseService;
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

    private final UserBaseService userBaseService;

    @Autowired
    private UserBaseMapper userMapper;

    public APIUserController(UserBaseService userBaseService) { this.userBaseService = userBaseService; }

    @GetMapping("/all")
    private List<UserBase> getAllUsers() {
        return userBaseService.getAll();
    }

    @GetMapping("/{id}")
    public UserBase getUserById(@PathVariable("id") long id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        return userBase;
    }

    @GetMapping("/{id}/description")
    public String getUserStringByid(@PathVariable("id") long id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        return userBase.toString();
    }

    @GetMapping()
    public List<UserBase> getUsersByName(@RequestParam("name") String name) {
        return userBaseService.getUsersByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserBaseDto dto) throws Exception {
        UserBase user = userBaseService.create(dto);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addUser(@Valid @RequestBody UserBase userBase) {
//        userBaseService.add(userBase);
//        return new ResponseEntity<>(userBase, HttpStatus.CREATED);
//    }

    @GetMapping("/nextid")
    public long getNextId() { return userBaseService.getNextId(); }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateUser(@PathVariable("id") long id, @RequestBody UserBaseDto userBaseDto) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        log.info(String.format("User: %s\nUserDto: %s", userBase.toString(), userBaseDto.toString()));
        userBaseService.update(userBase, userBaseDto);
        return ResponseEntity.ok(userBase.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        String deletionMsg = userBaseService.delete(userBase);
        return deletionMsg;
    }
}
