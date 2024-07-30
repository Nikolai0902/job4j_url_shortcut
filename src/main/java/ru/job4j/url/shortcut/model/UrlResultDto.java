package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных DTO для кода переадресации.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlResultDto {

    private String code;
}
