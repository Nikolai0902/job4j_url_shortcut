package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.job4j.url.shortcut.exception.ServiceException;
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

    public UrlResultDto save(UrlDto address) throws ServiceException {
        Url url = new Url();
        url.setUrl(address.getAddress());
        url.setKey(new RandomGenerator().generate());
        try {
            var result = urlRepository.save(url);
            return new UrlResultDto(result.getKey());
        } catch (Exception e) {
            log.error("Url уже существует", e);
            throw new ServiceException("Url уже существует", e);
        }
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
