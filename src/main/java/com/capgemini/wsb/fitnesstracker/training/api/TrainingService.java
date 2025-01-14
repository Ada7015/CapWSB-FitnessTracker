package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    /**
     * Retrieves all {@link Training} entities.
     *
     * @return a {@link List} of all {@link Training} entities
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all {@link Training} entities associated with a specific user.
     *
     * @param userId the ID of the user whose trainings are to be retrieved; must not be null
     * @return a {@link List} of {@link Training} entities associated with the specified user
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves all {@link Training} entities that started after a specific date.
     *
     * @param startDate the {@link Date} to filter trainings that started after; must not be null
     * @return a {@link List} of {@link Training} entities with a start time after the specified date
     */
    List<Training> getTrainingsByStartTime(Date startDate);

    /**
     * Retrieves all {@link Training} entities of a specific {@link ActivityType}.
     *
     * @param activityType the {@link ActivityType} to filter trainings by; must not be null
     * @return a {@link List} of {@link Training} entities that match the specified activity type
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);

    /**
     * Creates a new {@link Training} entity based on the provided data transfer object (DTO).
     *
     * @param createTrainingDto the {@link CreateUpdateTrainingDto} containing data for the new training; must not be null
     * @return the created {@link Training} entity
     */
    Training createTraining(CreateUpdateTrainingDto createTrainingDto);

    /**
     * Updates an existing {@link Training} entity with the provided data transfer object (DTO).
     *
     * @param trainingId the ID of the {@link Training} entity to update; must not be null
     * @param updateTrainingDto the {@link CreateUpdateTrainingDto} containing updated data for the training; must not be null
     * @return the updated {@link Training} entity
     */
    Training updateTraining(Long trainingId, CreateUpdateTrainingDto updateTrainingDto);
}

