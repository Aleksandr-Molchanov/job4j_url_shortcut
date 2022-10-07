package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Person;
import ru.job4j.shortcut.repository.PersonRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    public void delete(Person person) {
        repository.delete(person);
    }

    public Optional<Person> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public String generatePassword() {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int passwordLength = 7;
        SecureRandom rnd = new SecureRandom();
        StringBuilder password = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            password.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        for (Person el : repository.findAll()) {
            if (password.toString().equals(el.getPassword())) {
                generatePassword();
            }
        }
        return password.toString();
    }
}
