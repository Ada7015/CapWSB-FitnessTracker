package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    /**
     * Creates a new {@link User} entity and saves it in the database.
     *
     * @param user the {@link User} entity to be created; must not be null
     * @return the created {@link User} entity with an assigned ID
     * @throws IllegalArgumentException if the {@code user} has an existing ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a {@link User} entity by its unique ID.
     *
     * @param userId the ID of the {@link User} to retrieve; must not be null
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a {@link User} entity by its email address.
     *
     * @param email the email address of the user to retrieve; must not be null
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all {@link User} entities.
     *
     * @return a list of all {@link User} entities
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finds a {@link User} by their unique ID.
     *
     * @param id the ID of the user to retrieve; must not be null
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a {@link User} by their first name.
     *
     * @param fistName the first name of the user to retrieve; must not be null
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    public Optional<User> findUserByFirstName(String fistName) {
        return userRepository.findByFirstName(fistName);
    }

    /**
     * Finds a {@link User} by their last name.
     *
     * @param lastName the last name of the user to retrieve; must not be null
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    public Optional<User> findUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    /**
     * Finds all {@link User} entities that are older than a specified age.
     *
     * @param age the age threshold to check against
     * @return a list of {@link User} entities older than the specified age
     */
    public List<User> findOlderUsers(Long age) {
        return userRepository.findAllByBirthdateBefore(LocalDate.now().minusYears(age));
    }

    /**
     * Updates an existing {@link User} entity and saves it in the database.
     *
     * @param user the {@link User} entity with updated information; must not be null
     * @return the updated {@link User} entity
     * @throws IllegalArgumentException if the {@code user} is null
     */
    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a {@link User} entity by its unique ID.
     *
     * @param id the ID of the user to delete; must not be null
     * @throws NotFoundException if no user with the specified ID exists
     */
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)){
            throw new NotFoundException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    /**
     * Finds a user by their email address and converts it to a {@link UserDto}.
     *
     * @param email the email address of the user to find; must not be null
     * @return the {@link UserDto} of the found user
     * @throws NotFoundException if no user with the specified email exists
     */
    public UserDto findByEmail(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " does not exist"));
        return userMapper.toDto(user);
    }
}