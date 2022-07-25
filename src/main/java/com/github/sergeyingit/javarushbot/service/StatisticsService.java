package com.github.sergeyingit.javarushbot.service;

import com.github.sergeyingit.javarushbot.dto.StatisticDTO;

/**
 * Service for getting bot statistics.
 */
public interface StatisticsService {
    StatisticDTO countBotStatistic();
}
