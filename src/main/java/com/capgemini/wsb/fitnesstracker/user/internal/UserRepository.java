package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches for a user by their email address using an exact match.
     * This method streams through all users to find the first match.
     *
     * @param email the email of the user to search for
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user matches the email
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Searches for a user by their first name using an exact match.
     *
     * @param firstName the first name of the user to search for
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user matches the first name
     */
    Optional<User> findByFirstName(String firstName);

    /**
     * Searches for a user by their last name using an exact match.
     *
     * @param lastName the last name of the user to search for
     * @return an {@link Optional} containing the found user, or {@link Optional#empty()} if no user matches the last name
     */
    Optional<User> findByLastName(String lastName);

    /**
     * Retrieves a list of users whose birthdate is before the specified date.
     *
     * @param birthdate the date to compare user birthdates against
     * @return a {@link List} of {@link User} entities born before the specified date
     */
    List<User> findAllByBirthdateBefore(LocalDate birthdate);
}