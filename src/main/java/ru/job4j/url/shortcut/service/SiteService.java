package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.model.SiteDTO;
import ru.job4j.url.shortcut.model.SiteResultDTO;
import ru.job4j.url.shortcut.repository.SiteRepository;
import ru.job4j.url.shortcut.util.RandomGenerator;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SiteService implements UserDetailsService {

    private final SiteRepository siteRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<SiteResultDTO> save(SiteDTO site) {
        Optional<SiteResultDTO> dtoOptional = Optional.empty();
        try {
            dtoOptional = Optional.of(
                    new SiteResultDTO(false,
                            new RandomGenerator().generate(),
                            new RandomGenerator().generate()
                    ));
            Site newSite = new Site();
            newSite.setSite(site.getSite());
            newSite.setLogin(dtoOptional.get().getLogin());
            newSite.setPassword(bCryptPasswordEncoder.encode(dtoOptional.get().getPassword()));
            siteRepository.save(newSite);
            dtoOptional.get().setRegistration(true);
        } catch (DataIntegrityViolationException e) {
            log.error("Сайт с этим login уже существует", e);
        }
        return dtoOptional;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = siteRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(
                user.getLogin(),
                user.getPassword(),
                Collections.emptyList());
    }

    public Site findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }
 }
