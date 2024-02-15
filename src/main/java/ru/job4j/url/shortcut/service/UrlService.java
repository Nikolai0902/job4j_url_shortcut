package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.*;
import ru.job4j.url.shortcut.repository.UrlRepository;
import ru.job4j.url.shortcut.util.RandomGenerator;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UrlService {

    private UrlRepository urlRepository;

    public Optional<Url> save(UrlDto address) {
        Optional<Url> urlOptional = Optional.empty();
        try {
            Url url = new Url();
            url.setUrl(address.getAddress());
            url.setKey(new RandomGenerator().generate());
            urlOptional = Optional.of(urlRepository.save(url));
        } catch (DataIntegrityViolationException e) {
            log.error("Url уже существует", e);
        }
        return urlOptional;
    }

    public Optional<Url> findByKey(String key) {
        return urlRepository.findByKey(key);
    }

    public void incrementCount(String url) {
        urlRepository.incrementCount(url);
    }

    public List<Url> findAll() {
        return urlRepository.findAll();
    }
}
