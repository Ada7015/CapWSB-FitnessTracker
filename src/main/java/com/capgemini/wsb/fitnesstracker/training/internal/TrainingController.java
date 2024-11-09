package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    /**
     * Retrieves a list of all trainings.
     *
     * @return a list of TrainingsDto objects representing all trainings
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a trainings by user ID.
     *
     * @param userId the ID of the user
     * @return the TrainingDto of the found user's trainings
     * @throws UserNotFoundException if no training for user with the given ID is found
     */
    @GetMapping("/get-user-trainings/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all ended trainings defined by end date.
     *
     * @param endDate the end date
     * @return the TrainingDto of the found trainings which ended before end date
     * @throws UserNotFoundException if no training for defined end date is found
     */
    @GetMapping("/get-trainings-by-end-date")
    public List<TrainingDto> getTrainingsByEndDate(@RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return trainingService.getTrainingsByEndDate(endDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings by specific activity.
     *
     * @param activityType is activity type
     * @return the TrainingDto of the found trainings with defined activity
     * @throws UserNotFoundException if no training for defined activity is found
     */
    @GetMapping("/get-trainings-by-activity-type/{activity}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable("activity") String activityType) {
        return trainingService.getTrainingsByActivityType(ActivityType.valueOf(activityType.toUpperCase()))
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
