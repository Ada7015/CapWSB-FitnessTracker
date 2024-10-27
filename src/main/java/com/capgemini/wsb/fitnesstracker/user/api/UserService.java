package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new {@link User} entity and saves it in the database.
     *
     * @param user the {@link User} entity to be created; must not be null
     * @return the created {@link User} entity with an assigned ID
     * @throws IllegalArgumentException if the {@code user} parameter is null
     */
    User createUser(User user);

    /**
     * Deletes an existing {@link User} entity by its ID.
     *
     * @param id the ID of the {@link User} entity to be deleted; must not be null
     * @throws IllegalArgumentException if the {@code id} parameter is null
     * @throws UserNotFoundException if no user with the specified ID is found
     */
    void deleteUser(Long id);

}
