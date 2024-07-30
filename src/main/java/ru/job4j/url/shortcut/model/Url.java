package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Модель данных URl.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @NotNull
    private int id;
    private int count = 0;
    private String url;

    @Column(name = "hash_code")
    private String hashCode;

    public Url(String url, String hashCode) {
        this.url = url;
        this.hashCode = hashCode;
    }
}
