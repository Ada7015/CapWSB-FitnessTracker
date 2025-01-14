package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateUpdateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

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
     * @param startTime the end date
     * @return the TrainingDto of the found trainings which ended before end date
     *
     */
    @GetMapping("/get-trainings-by-start-date")
    public List<TrainingDto> getTrainingsByStartTime(@RequestParam("startTime") @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime) {
        return trainingService.getTrainingsByStartTime(startTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings by specific activity.
     *
     * @param activityType is activity type
     * @return the TrainingDto of the found trainings with defined activity
     *
     */
    @GetMapping("/get-trainings-by-activity-type/{activity}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable("activity") String activityType) {
        return trainingService.getTrainingsByActivityType(ActivityType.valueOf(activityType.toUpperCase()))
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Creates new training
     *
     * @param createTrainingDto is a new training to save
     * @return the newly created trainingDto
     *
     */
    @PostMapping("/create-training")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody() CreateUpdateTrainingDto createTrainingDto) {
        return trainingMapper.toDto(trainingService.createTraining(createTrainingDto));
    }

    /**
     * Update existing training
     *
     * @param trainingId is id of training that needs to be updated
     * @param createTrainingDto is updated training
     * @return the newly created trainingDto
     *
     */
    @PutMapping("/update-training/{trainingId}")
    public TrainingDto updateTraining(@PathVariable("trainingId") Long trainingId, @RequestBody() CreateUpdateTrainingDto createTrainingDto) {
        return trainingMapper.toDto(trainingService.updateTraining(trainingId, createTrainingDto));
    }
}
