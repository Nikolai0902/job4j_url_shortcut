package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.exception.ControllerException;
import ru.job4j.url.shortcut.exception.ServiceException;
import ru.job4j.url.shortcut.model.SiteDTO;
import ru.job4j.url.shortcut.model.SiteResultDTO;
import ru.job4j.url.shortcut.service.SiteService;

import javax.validation.Valid;


/**
 * Класс-контроллер регистрации сайта
 */
@RestController
@AllArgsConstructor
public class SiteController {

    private final SiteService siteService;

    /**
     * Метод регитсрации нового сайта.
     */
    @PostMapping("/registration")
    public ResponseEntity<SiteResultDTO> registration(@Valid @RequestBody SiteDTO siteDTO) throws ControllerException {
        try {
            var result = this.siteService.save(siteDTO);
            return ResponseEntity.ok(result);
        } catch (ServiceException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(
                        new SiteResultDTO(false, null, null),
                        HttpStatus.CONFLICT);
            }
            throw new ControllerException("не удается сохранить сайт", e);
        }
    }
}
