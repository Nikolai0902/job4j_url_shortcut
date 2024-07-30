package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Site;

/**
 * Реализация хранилища сайтов с помощью Spring Data
 */
public interface SiteRepository extends CrudRepository<Site, Integer> {

    Site findByLogin(String login);
}
