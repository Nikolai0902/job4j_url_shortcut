package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных DTO авторизации.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteResultDTO {

    private Boolean registration = false;
    private String login;
    private String password;
}
