package ru.job4j.url.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.url.shortcut.model.Url;

import java.util.List;
import java.util.Optional;

/**
 * Реализация хранилища URL с помощью Spring Data
 */
public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findByHashCode(String code);

    List<Url> findAll();

    @Modifying
    @Transactional
    @Query("UPDATE Url a SET a.count = a.count + 1 WHERE a.url = :url")
    void incrementCount(String url);
}
