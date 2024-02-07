package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.model.SiteDTO;
import ru.job4j.url.shortcut.model.SiteResultDTO;
import ru.job4j.url.shortcut.service.SiteService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @PostMapping("/registration")
    public ResponseEntity<SiteResultDTO> registration(@Valid @RequestBody SiteDTO siteDTO) {
        var result = this.siteService.save(siteDTO);
        if (!result.get().getRegistration()) {
            return new ResponseEntity<>(
                    new SiteResultDTO(false, null, null),
                    HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(result.get());
    }
}
