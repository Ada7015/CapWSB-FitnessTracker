package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

public record CreateUpdateTrainingDto(Date startTime,
                                Date endTime, ActivityType activityType,
                                double distance, double averageSpeed, Long userId) {
}
