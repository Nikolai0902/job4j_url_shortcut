package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SiteResultDTO {

    private Boolean registration = false;
    private String login;
    private String password;
}
