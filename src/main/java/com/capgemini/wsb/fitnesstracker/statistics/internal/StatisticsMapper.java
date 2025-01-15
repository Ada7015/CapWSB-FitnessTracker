package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import org.springframework.stereotype.Component;

@Component
class StatisticsMapper {

    Statistics toEntity(StatisticsDto dto) {
        return new Statistics(
                dto.user(),
                dto.totalTrainings(),
                dto.totalDistance(),
                dto.totalCaloriesBurned()
        );
    }

    StatisticsDto toDto(Statistics entity) {
        return new StatisticsDto(entity.getId(), entity.getUser(), entity.getTotalTrainings(), entity.getTotalDistance(), entity.getTotalCaloriesBurned());
    }
}
