package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(Long userId);

    List<Training> getTrainingsByStartTime(Date startDate);

    List<Training> getTrainingsByActivityType(ActivityType activityType);

    Training createTraining(CreateUpdateTrainingDto createTrainingDto);

    Training updateTraining(Long trainingId, CreateUpdateTrainingDto updateTrainingDto);
}
