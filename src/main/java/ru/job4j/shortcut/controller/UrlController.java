package ru.job4j.shortcut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.model.dto.StatisticDTO;
import ru.job4j.shortcut.model.dto.UrlDTO;
import ru.job4j.shortcut.model.dto.CodeDTO;
import ru.job4j.shortcut.service.UrlService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteController.class.getSimpleName());

    private final UrlService urls;

    public UrlController(final UrlService urls) {
        this.urls = urls;
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeDTO> convert(@Valid @RequestBody UrlDTO reqRegistration) {
        CodeDTO rsl = new CodeDTO();
        Url url = new Url();
        url.setUrl(reqRegistration.getUrl());
        url.setCode(urls.generateCode());
        try {
            urls.save(url);
            rsl.setCode(url.getCode());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            Optional<Url> find = urls.findAll().stream()
                    .filter(data -> Objects.equals(data, reqRegistration.getUrl()))
                    .findFirst();
            rsl.setCode(find.get().getCode());
        }
        return new ResponseEntity<CodeDTO>(rsl, HttpStatus.OK);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@Valid @PathVariable String code) {
        var findUrl = this.urls.findByCode(code);
        Url url = findUrl.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        urls.incrementTotal(url.getCode());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header("REDIRECT", url.getUrl())
                .build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<Set<StatisticDTO>> statistic() {
        var findUrls = this.urls.findAll();
        Set<StatisticDTO> rsl = new HashSet<>();
        for (Url el : findUrls) {
            StatisticDTO temp = new StatisticDTO();
            temp.setUrl(el.getUrl());
            temp.setTotal(el.getTotal());
            rsl.add(temp);
        }
        return new ResponseEntity<>(rsl, HttpStatus.OK);
    }
}
