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
import java.util.UUID;

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
    public UserBase getUserById(@PathVariable("id") UUID id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        return userBase;
    }

    @GetMapping("/{id}/description")
    public String getUserStringByid(@PathVariable("id") UUID id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        return userBase.toString();
    }

    @GetMapping()
    public List<UserBase> getUsersByName(@RequestParam("name") String name) {
        return userBaseService.getUsersByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<UserBase> addUser(@Valid @RequestBody UserBaseDto dto) throws Exception {
        UserBase user = userBaseService.create(dto);
        log.info("DTO vs USER\n");
        log.info(dto.getName() + " ------- " + user.getName() + "\n");
        log.info(dto.getEmail() + " ------- " + user.getEmail() + "\n");
        log.info(dto.getLoginName() + " ------- " + user.getLoginName() + "\n");
        log.info(dto.getPassword() + " ------- " + user.getPasswordHash() + "\n");
        log.info(userMapper.passwordEncoder + "\n");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addUser(@Valid @RequestBody UserBase userBase) {
//        userBaseService.add(userBase);
//        return new ResponseEntity<>(userBase, HttpStatus.CREATED);
//    }

//    @GetMapping("/nextid")
//    public long getNextId() { return userBaseService.getNextId(); }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> partialUpdateUser(@PathVariable("id") UUID id, @RequestBody UserBaseDto userBaseDto) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        log.info(String.format("User: %s\nUserDto: %s", userBase.toString(), userBaseDto.toString()));
        userBaseService.update(userBase, userBaseDto);
        return ResponseEntity.ok(userBase.toString() + " updated!");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        UserBase userBase = userBaseService.getUserByIdOrError(id);
        String deletionMsg = userBaseService.delete(userBase);
        return deletionMsg;
    }
}
