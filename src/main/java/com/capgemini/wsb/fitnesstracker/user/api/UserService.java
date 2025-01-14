package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Creates a new {@link User} entity and saves it in the database.
     *
     * @param user the {@link User} entity to be created; must not be null
     * @return the created {@link User} entity with an assigned ID
     */
    User createUser(User user);

    /**
     * Deletes an existing {@link User} entity by its ID.
     *
     * @param id the ID of the {@link User} entity to be deleted; must not be null
     */
    void deleteUser(Long id);

    /**
     * Finds a {@link User} entity by its ID.
     *
     * @param id the ID of the {@link User} entity to find; must not be null
     * @return an {@link Optional} containing the found {@link User}, or {@link Optional#empty()} if no user is found
     */
    Optional<User> findUserById(Long id);

    /**
     * Finds a {@link User} entity by its first name using an exact match.
     *
     * @param firstName the first name of the {@link User} entity to find; must not be null
     * @return an {@link Optional} containing the found {@link User}, or {@link Optional#empty()} if no user is found
     */
    Optional<User> findUserByFirstName(String firstName);

    /**
     * Finds a {@link User} entity by its last name using an exact match.
     *
     * @param lastName the last name of the {@link User} entity to find; must not be null
     * @return an {@link Optional} containing the found {@link User}, or {@link Optional#empty()} if no user is found
     */
    Optional<User> findUserByLastName(String lastName);

    /**
     * Retrieves a list of users who are older than a specified age.
     *
     * @param age the minimum age to filter {@link User} entities; must not be null
     * @return a {@link List} of {@link User} entities older than the specified age
     */
    List<User> findOlderUsers(Long age);

    /**
     * Updates an existing {@link User} entity with new data.
     *
     * @param user the {@link User} entity containing updated information; must not be null
     * @param id the ID of the {@link User} entity to be updated; must not be null
     * @return the updated {@link User} entity
     */
    User updateUser(User user, Long id);

    /**
     * Finds a {@link User} entity by its email address using an exact match.
     *
     * @param email the email address of the {@link User} entity to find; must not be null
     * @return the found {@link User} entity
     */
    User findByEmail(String email);
}
