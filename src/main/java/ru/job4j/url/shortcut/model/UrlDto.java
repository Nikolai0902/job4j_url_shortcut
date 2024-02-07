package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    @NotBlank(message = "Url must be not empty")
    @Size(min = 5, max = 2000, message
            = "Url must be between 5 and 20 characters")
    private String address;
}
