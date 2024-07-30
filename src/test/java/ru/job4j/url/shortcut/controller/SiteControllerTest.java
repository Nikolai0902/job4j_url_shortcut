package ru.job4j.url.shortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.job4j.url.shortcut.model.SiteDTO;
import ru.job4j.url.shortcut.model.SiteResultDTO;
import ru.job4j.url.shortcut.service.SiteService;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ActiveProfiles("test")
class SiteControllerTest {

    @Autowired
    private SiteController siteController;

    @MockBean
    private SiteService siteService;

    @Test
    public void whenRegistrationReturnOk() throws Exception {
        SiteDTO siteDTO = new SiteDTO("site");
        SiteResultDTO ex = new SiteResultDTO(true, "login", "12345");
        when(siteService.save(siteDTO)).thenReturn(ex);
        ResponseEntity<SiteResultDTO> result = siteController.registration(siteDTO);
        assertThat(result).isEqualTo(
                ResponseEntity.ok(ex));
    }
}