package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

public interface StatisticsService {

    /**
     * Creates a new statistics entry.
     *
     * @param statisticsDTO the data transfer object containing statistics information to create.
     * @return the created statistics wrapped in a DTO.
     */
    StatisticsDto createStatistics(StatisticsDto statisticsDTO);

    /**
     * Updates an existing statistics entry identified by its ID.
     *
     * @param id the ID of the statistics entry to update.
     * @param statisticsDTO the data transfer object containing the updated statistics information.
     * @return the updated statistics wrapped in a DTO.
     */
    StatisticsDto updateStatistics(Long id, StatisticsDto statisticsDTO);

    /**
     * Retrieves the statistics by its ID.
     *
     * @param id the ID of the statistics entry to retrieve.
     * @return an Optional containing the statistics if found, or an empty Optional if not found.
     */
    Optional<Statistics> getStatisticsById(Long id);

    /**
     * Deletes the statistics entry identified by its ID.
     *
     * @param id the ID of the statistics entry to delete.
     */
    void deleteStatistics(Long id);

    /**
     * Searches for statistics entries based on a minimum calorie value.
     *
     * @param minCalories the minimum calorie value to search for.
     * @return a list of StatisticsDto objects matching the search criteria.
     */
    List<StatisticsDto> searchByCalories(int minCalories);
}
