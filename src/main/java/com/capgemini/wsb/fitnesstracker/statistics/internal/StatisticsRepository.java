package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    /**
     * Finds all statistics entries where the total calories burned is greater than the specified minimum value.
     *
     * @param minCalories the minimum number of calories burned to filter the statistics entries.
     * @return a list of {@link Statistics} entities that meet the criteria.
     */
    List<Statistics> findAllByTotalCaloriesBurnedAfter(int minCalories);
}
