package ru.job4j.url.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url.shortcut.model.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    Site findByLogin(String login);
}
