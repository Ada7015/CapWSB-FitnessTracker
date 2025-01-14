package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateUpdateTrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserService userService;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsByStartTime(Date startDate) {
        return trainingRepository.findAllByStartTimeAfter(startDate);
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }

    @Override
    public Training createTraining(CreateUpdateTrainingDto createTrainingDto) {
        User user = userService.findUserById(createTrainingDto.userId()).orElseThrow();
        Training newTraining = new Training(
                user,
                createTrainingDto.startTime(),
                createTrainingDto.endTime(),
                createTrainingDto.activityType(),
                createTrainingDto.distance(),
                createTrainingDto.averageSpeed()
        );
        return trainingRepository.save(newTraining);
    }

    @Override
    public Training updateTraining(Long trainingId, CreateUpdateTrainingDto updateTrainingDto) {
        User user = userService.findUserById(updateTrainingDto.userId()).orElseThrow();
        Training newTraining = new Training(
                trainingId,
                user,
                updateTrainingDto.startTime(),
                updateTrainingDto.endTime(),
                updateTrainingDto.activityType(),
                updateTrainingDto.distance(),
                updateTrainingDto.averageSpeed()
        );
        return trainingRepository.save(newTraining);
    }
}
