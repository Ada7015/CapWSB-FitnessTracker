package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing {@link Training} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations and additional query methods for {@link Training}.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all {@link Training} entities associated with a specific user.
     *
     * @param userId the ID of the user whose trainings are to be retrieved; must not be null
     * @return a {@link List} of {@link Training} entities associated with the specified user
     */
    List<Training> findAllByUserId(Long userId);

    /**
     * Finds all {@link Training} entities that started after a specific date and time.
     *
     * @param startTime the {@link Date} to filter trainings that started after; must not be null
     * @return a {@link List} of {@link Training} entities with a start time after the specified date
     */
    List<Training> findAllByStartTimeAfter(Date startTime);

    /**
     * Finds all {@link Training} entities of a specific {@link ActivityType}.
     *
     * @param activityType the {@link ActivityType} to filter trainings by; must not be null
     * @return a {@link List} of {@link Training} entities that match the specified activity type
     */
    List<Training> findAllByActivityType(ActivityType activityType);
}
