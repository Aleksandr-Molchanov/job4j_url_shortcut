package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Person;
import ru.job4j.shortcut.model.dto.ReqRegistrationPersonDTO;
import ru.job4j.shortcut.model.dto.RespRegistrationPersonDTO;
import ru.job4j.shortcut.service.PersonService;

import javax.validation.Valid;
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
    public ResponseEntity<RespRegistrationPersonDTO> registration(@Valid @RequestBody ReqRegistrationPersonDTO reqRegistration) {
        Optional<Person> person = persons.findByUsername(reqRegistration.getSite());
        RespRegistrationPersonDTO rsl = new RespRegistrationPersonDTO();
        if (person.isPresent()) {
            Person user = person.get();
            rsl.setRegistration(false);
            rsl.setLogin(user.getLogin());
            rsl.setPassword(user.getPassword());
            return new ResponseEntity<RespRegistrationPersonDTO>(rsl, HttpStatus.OK);
        }
        rsl.setRegistration(true);
        rsl.setLogin(reqRegistration.getSite());
        rsl.setPassword(persons.generatePassword());
        Person user = new Person();
        user.setSite(reqRegistration.getSite());
        user.setLogin(reqRegistration.getSite());
        user.setPassword(encoder.encode(rsl.getPassword()));
        persons.save(user);
        return new ResponseEntity<RespRegistrationPersonDTO>(rsl, HttpStatus.CREATED);
    }

}