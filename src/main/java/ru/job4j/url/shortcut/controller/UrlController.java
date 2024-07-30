package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.shortcut.exception.ControllerException;
import ru.job4j.url.shortcut.exception.ServiceException;
import ru.job4j.url.shortcut.model.StatisticDTO;
import ru.job4j.url.shortcut.model.UrlDto;
import ru.job4j.url.shortcut.model.UrlResultDto;
import ru.job4j.url.shortcut.service.SiteService;
import ru.job4j.url.shortcut.service.UrlService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    /**
     * Метод регистрации URL.
     * Пользователь должен быть авторизирован.
     */
    @PostMapping("/convert")
    public ResponseEntity<UrlResultDto> convert(@Valid @RequestBody UrlDto address) throws ControllerException {
        try {
            var result = this.urlService.save(address);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            throw new ControllerException("не удается преобразовать", e);
        }
    }

    /**
     * Метод переадресации.
     * Без авторизации.
     */
    @GetMapping("/redirect/{key}")
    public ResponseEntity<?> convert(@PathVariable String key) {
        var codeOpt = urlService.findByKey(key);
        if (codeOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code is not found.");
        }
        urlService.incrementCount(codeOpt.get().getUrl());
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("HTTP CODE", "302 REDIRECT URL")
                .body(Map.of("url", codeOpt.get().getUrl()));
    }

    /**
     * Статистика запросов url.
     */
    @GetMapping("/statistic")
    public ResponseEntity<?> statistic() {
        Collection<StatisticDTO> map = urlService.findAll()
                .stream()
                .map(u -> new StatisticDTO(u.getUrl(), u.getCount()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(map);
    }
}
