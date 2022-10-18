package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Site;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    @Override
    List<Site> findAll();

    Optional<Site> findByDomain(String username);

}