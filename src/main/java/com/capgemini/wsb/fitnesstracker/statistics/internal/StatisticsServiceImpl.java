package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsMapper statisticsMapper;

    @Override
    public StatisticsDto createStatistics(StatisticsDto statisticsDTO) {
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        return statisticsMapper.toDto(statisticsRepository.save(statistics));
    }

    @Override
    public StatisticsDto updateStatistics(Long id, StatisticsDto statisticsDTO) {
        if (!statisticsRepository.existsById(id)) {
            throw new NotFoundException("Statistics with id " + id + " does not exist");
        }
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        statistics.setId(id);
        return statisticsMapper.toDto(statisticsRepository.save(statistics));
    }

    @Override
    public Optional<Statistics> getStatisticsById(Long id) {
        return statisticsRepository.findById(id);
    }

    @Override
    public void deleteStatistics(Long id) {
        if (!statisticsRepository.existsById(id)){
            throw new NotFoundException("Statistics with id " + id + " does not exist");
        }
        statisticsRepository.deleteById(id);
    }

    @Override
    public List<StatisticsDto> searchByCalories(int minCalories) {
        return statisticsRepository.findAllByTotalCaloriesBurnedAfter(minCalories)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }
}
