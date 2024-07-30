package ru.job4j.url.shortcut.controller;


import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import ru.job4j.url.shortcut.model.Url;
import ru.job4j.url.shortcut.model.UrlDto;
import ru.job4j.url.shortcut.model.UrlResultDto;
import static org.assertj.core.api.Assertions.*;

import ru.job4j.url.shortcut.service.SiteService;
import ru.job4j.url.shortcut.service.UrlService;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest()
class UrlControllerTest {

    @Autowired
    private UrlController urlController;

    @MockBean
    private UrlService urlService;

    @MockBean
    private SiteService siteService;

    @Test
    @WithMockUser
    public void whenConvertReturnOk() throws Exception {
        UrlDto urlDto = new UrlDto("www.site.ru");
        UrlResultDto ex = new UrlResultDto("121212");
        when(urlService.save(urlDto)).thenReturn(ex);
        ResponseEntity<UrlResultDto> result = urlController.convert(urlDto);
        assertThat(result).isEqualTo(
                ResponseEntity.ok(ex));
    }

    @Test
    @WithMockUser
    public void whenUnConvertReturnOk() throws Exception {
        Optional<Url> url = Optional.of(new Url("url", "hashCode"));
        when(urlService.findByKey("key")).thenReturn(url);
        ResponseEntity<?> result = urlController.convert("key");
        assertThat(result).isEqualTo(
                ResponseEntity.status(HttpStatus.FOUND)
                        .header("HTTP CODE", "302 REDIRECT URL")
                        .body(Map.of("url", url.get().getUrl())));
    }
}