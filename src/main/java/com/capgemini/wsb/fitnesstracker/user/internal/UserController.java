package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.userBasicInfo.UserBasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("basic-information")
    public List<UserBasicInfo> getBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserBasicInfo)
                .toList();
    }

    @GetMapping("get-user-by-id/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("get-user-by-first-name/{firstName}")
    public UserDto getUserByFirstName(@PathVariable String firstName) throws NoSuchElementException {
        return userService.findUserByFirstName(firstName)
                .map(userMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("get-user-by-last-name/{lastName}")
    public UserDto getUserByLastName(@PathVariable String lastName) throws NoSuchElementException {
        return userService.findUserByLastName(lastName)
                .map(userMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("find-older-users/{age}")
    public List<UserDto> findOlderUsers(@PathVariable Long age) throws ResponseStatusException {
        List<UserDto> users = userService.findOlderUsers(age)
                .stream()
                .map(userMapper::toDto)
                .toList();

        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found older than ${age}");
        }

        return users;
    }

    @PostMapping("add-user")
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userService.createUser(userMapper.toEntity(userDto));
    }

}