package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.url.shortcut.model.Url;
import ru.job4j.url.shortcut.model.UrlDto;
import ru.job4j.url.shortcut.model.UrlResultDto;
import ru.job4j.url.shortcut.service.SiteService;
import ru.job4j.url.shortcut.service.UrlService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final SiteService siteService;

    @PostMapping("/convert")
    public ResponseEntity<UrlResultDto> convert(@Valid @RequestBody UrlDto address) {
        String site = siteService.findByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName()).getSite();
        address.setAddress(site + "/" + address.getAddress());
        var result = this.urlService.save(address);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url уже существует");
        }
        return ResponseEntity.ok(new UrlResultDto(result.get().getKey()));
    }

    @GetMapping("/redirect/{key}")
    public ResponseEntity<?> convert(@PathVariable String key) {
        var codeOpt = urlService.findByKey(key);
        if (codeOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code is not found.");
        }
        urlService.incrementCount(codeOpt.get().getUrl());
        return new ResponseEntity(
                new MultiValueMapAdapter<>(Map.of(
                        "HTTP CODE",
                        List.of(codeOpt.get().getUrl()))),
                HttpStatus.FOUND
        );
    }

    @GetMapping("/statistic")
    public ResponseEntity<Map<String, String>> statistic() {
        Map<String, String> map = urlService.findAll().stream()
                .collect(Collectors.toMap(
                        url1 -> "url: " + url1.getUrl(),
                        url -> "total: " + url.getCount()));
        return ResponseEntity.ok(map);
    }
}
