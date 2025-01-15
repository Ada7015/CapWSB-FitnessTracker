package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

public record StatisticsDto(Long id, User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {}
