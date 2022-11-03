package ru.job4j.shortcut.service;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private final SiteRepository repository;

    public SiteService(SiteRepository repository) {
        this.repository = repository;
    }

    public List<Site> findAll() {
        return repository.findAll();
    }

    public Site save(Site site) throws DataIntegrityViolationException {
        return repository.save(site);
    }

    public Optional<Site> findById(int id) {
        return repository.findById(id);
    }

    public void delete(Site site) {
        repository.delete(site);
    }

    public Optional<Site> findByDomain(String domain) {
        return repository.findByDomain(domain);
    }

    public String generatePassword() {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int passwordLength = 7;
        SecureRandom rnd = new SecureRandom();
        StringBuilder password = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            password.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return password.toString();
    }
}
