package ru.job4j.url.shortcut.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SiteDTO {

    @NotBlank(message = "Site must be not empty")
    @Size(min = 3, max = 20, message
            = "Login must be between 3 and 20 characters")
    private String site;
}
