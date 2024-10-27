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


    /**
     * Retrieves a list of all users.
     *
     * @return a list of UserDto objects representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Retrieves basic information for all users.
     *
     * @return a list of UserBasicInfo objects representing basic information of all users
     */
    @GetMapping("basic-information")
    public List<UserBasicInfo> getBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserBasicInfo)
                .toList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDto of the found user
     * @throws UserNotFoundException if no user with the given ID is found
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findUserById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Retrieves a user by their first name.
     *
     * @param firstName the first name of the user to retrieve
     * @return the UserDto of the found user
     * @throws NoSuchElementException if no user with the given first name is found
     */
    @GetMapping("get-user-by-first-name/{firstName}")
    public UserDto getUserByFirstName(@PathVariable String firstName) throws NoSuchElementException {
        return userService.findUserByFirstName(firstName)
                .map(userMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Retrieves a user by their last name.
     *
     * @param lastName the last name of the user to retrieve
     * @return the UserDto of the found user
     * @throws NoSuchElementException if no user with the given last name is found
     */
    @GetMapping("get-user-by-last-name/{lastName}")
    public UserDto getUserByLastName(@PathVariable String lastName) throws NoSuchElementException {
        return userService.findUserByLastName(lastName)
                .map(userMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return the UserDto of the found user
     * @throws UserNotFoundException if no user with the given email address is found
     */

    @GetMapping("get-user-by-email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    /**
     * Retrieves a list of users older than the specified age.
     *
     * @param age the age threshold for users to retrieve
     * @return a list of UserDto objects representing users older than the specified age
     * @throws ResponseStatusException if no users older than the specified age are found
     */
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
    /**
     * Adds a new user to the system.
     *
     * @param userDto the UserDto representing the user to add
     * @return the created User object
     */
    @PostMapping("add-user")
    public User addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userMapper.toEntity(userDto));
    }
    /**
     * Updates an existing user.
     *
     * @param updatedUserDto the UserDto representing the updated user data
     * @param id             the ID of the user to update
     * @return the updated User object
     * @throws ResponseStatusException if the user ID is missing or invalid
     */
    @PutMapping("/{id}")
    public User updateUser(@RequestBody UserDto updatedUserDto, @PathVariable Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Id needed to update user");
        }
        return userService.updateUser(userMapper.toEntity(updatedUserDto));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}