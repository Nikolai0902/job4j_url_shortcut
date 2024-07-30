package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных DTO результата статистики.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private String url;
    private int count;
}
