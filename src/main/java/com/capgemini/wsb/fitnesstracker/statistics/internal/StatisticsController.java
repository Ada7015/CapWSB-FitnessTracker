package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper;

    /**
     * Endpoint to create a new statistics entry.
     *
     * @param statisticsDTO the data transfer object containing statistics information to be created.
     * @return the created statistics wrapped in a DTO.
     */
    @PostMapping
    public StatisticsDto createStatistics(@RequestBody StatisticsDto statisticsDTO) {
        return statisticsService.createStatistics(statisticsDTO);
    }

    /**
     * Endpoint to update an existing statistics entry.
     *
     * @param id the ID of the statistics entry to update.
     * @param statisticsDTO the data transfer object containing updated statistics information.
     * @return the updated statistics wrapped in a DTO.
     */
    @PutMapping("/{id}")
    public StatisticsDto updateStatistics(@PathVariable Long id, @RequestBody StatisticsDto statisticsDTO) {
        return statisticsService.updateStatistics(id, statisticsDTO);
    }

    /**
     * Endpoint to retrieve statistics by its ID.
     * If the statistics with the provided ID is found, it will be returned;
     * otherwise, a {@link NotFoundException} will be thrown.
     *
     * @param id the ID of the statistics entry to retrieve.
     * @return the statistics wrapped in a DTO.
     * @throws NotFoundException if the statistics with the given ID does not exist.
     */
    @GetMapping("/{id}")
    public StatisticsDto getStatistics(@PathVariable Long id) throws NotFoundException {
        return statisticsService.getStatisticsById(id)
                .map(statisticsMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Statistics with ID " + id + " does not exist"));
    }

    /**
     * Endpoint to delete a statistics entry by its ID.
     * The HTTP status will be {@code 204 No Content} if the deletion is successful.
     *
     * @param id the ID of the statistics entry to delete.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStatistics(@PathVariable Long id) {
        statisticsService.deleteStatistics(id);
    }

    /**
     * Endpoint to search for statistics entries based on a minimum calorie value.
     *
     * @param minCalories the minimum calorie value to filter the statistics.
     * @return a list of {@link StatisticsDto} objects matching the search criteria.
     */
    @GetMapping("/search")
    public List<StatisticsDto> searchByCalories(@RequestParam int minCalories) {
        return statisticsService.searchByCalories(minCalories);
    }
}
