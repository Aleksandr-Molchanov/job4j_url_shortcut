package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.model.Person;
import ru.job4j.shortcut.model.dto.ReqRegistrationDTO;
import ru.job4j.shortcut.model.dto.RespRegistrationDTO;
import ru.job4j.shortcut.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class.getSimpleName());

    private final PersonService persons;

    private BCryptPasswordEncoder encoder;

    private final ObjectMapper objectMapper;

    public PersonController(final PersonService persons, BCryptPasswordEncoder encoder, ObjectMapper objectMapper) {
        this.persons = persons;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<RespRegistrationDTO> registration(@Valid @RequestBody ReqRegistrationDTO reqRegistration) {
        Optional<Person> person = persons.findByUsername(reqRegistration.getSite());
        RespRegistrationDTO rsl = new RespRegistrationDTO();
        if (person.isPresent()) {
            Person user = person.get();
            rsl.setRegistration(false);
            rsl.setLogin(user.getLogin());
            rsl.setPassword(user.getPassword());
            return new ResponseEntity<RespRegistrationDTO>(rsl, HttpStatus.OK);
        }
        Person user = new Person();
        user.setSite(reqRegistration.getSite());
        user.setLogin(reqRegistration.getSite());
        user.setPassword(encoder.encode(persons.generatePassword()));
        persons.save(user);
        rsl.setRegistration(true);
        rsl.setLogin(user.getLogin());
        rsl.setPassword(user.getPassword());
        return new ResponseEntity<RespRegistrationDTO>(rsl, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(this.persons.findAll());
    }

}