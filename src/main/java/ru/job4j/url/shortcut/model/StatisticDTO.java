package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private String url;
    private int count;
}
