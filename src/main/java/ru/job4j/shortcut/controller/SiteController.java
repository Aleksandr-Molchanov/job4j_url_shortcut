package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.dto.ReqRegistrationSiteDTO;
import ru.job4j.shortcut.model.dto.RespRegistrationSiteDTO;
import ru.job4j.shortcut.service.SiteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class SiteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class.getSimpleName());

    private final SiteService sites;

    private BCryptPasswordEncoder encoder;

    private final ObjectMapper objectMapper;

    public SiteController(final SiteService sites, BCryptPasswordEncoder encoder, ObjectMapper objectMapper) {
        this.sites = sites;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<RespRegistrationSiteDTO> registration(@Valid @RequestBody ReqRegistrationSiteDTO reqRegistration) {
        RespRegistrationSiteDTO rsl = new RespRegistrationSiteDTO();
        String password = sites.generatePassword();
        Site site = new Site();
        site.setDomain(reqRegistration.getSite());
        site.setLogin(reqRegistration.getSite());
        site.setPassword(encoder.encode(password));
        try {
            sites.save(site);
            rsl.setRegistration(true);
            rsl.setLogin(reqRegistration.getSite());
            rsl.setPassword(password);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            Site findSite = sites.findByDomain(reqRegistration.getSite()).get();
            rsl.setRegistration(false);
            rsl.setLogin(findSite.getLogin());
            rsl.setPassword(findSite.getPassword());
    }
        return new ResponseEntity<RespRegistrationSiteDTO>(rsl, HttpStatus.OK);
    }

}