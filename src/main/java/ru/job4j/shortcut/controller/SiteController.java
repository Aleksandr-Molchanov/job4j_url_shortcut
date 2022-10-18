package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.dto.ReqRegistrationSiteDTO;
import ru.job4j.shortcut.model.dto.RespRegistrationSiteDTO;
import ru.job4j.shortcut.service.SiteService;

import javax.validation.Valid;
import java.util.Optional;

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
        Optional<Site> optionalSite = sites.findByDomain(reqRegistration.getSite());
        RespRegistrationSiteDTO rsl = new RespRegistrationSiteDTO();
        if (optionalSite.isPresent()) {
            Site site = optionalSite.get();
            rsl.setRegistration(false);
            rsl.setLogin(site.getLogin());
            rsl.setPassword(site.getPassword());
            return new ResponseEntity<RespRegistrationSiteDTO>(rsl, HttpStatus.OK);
        }
        rsl.setRegistration(true);
        rsl.setLogin(reqRegistration.getSite());
        rsl.setPassword(sites.generatePassword());
        Site user = new Site();
        user.setSite(reqRegistration.getSite());
        user.setLogin(reqRegistration.getSite());
        user.setPassword(encoder.encode(rsl.getPassword()));
        sites.save(user);
        return new ResponseEntity<RespRegistrationSiteDTO>(rsl, HttpStatus.CREATED);
    }

}