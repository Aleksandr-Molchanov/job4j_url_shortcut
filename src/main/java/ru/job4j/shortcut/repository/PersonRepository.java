package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    List<Person> findAll();

    Optional<Person> findByUsername(String username);

}