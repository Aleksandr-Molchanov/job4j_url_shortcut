package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.repository.UrlRepository;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Set;

@Service
public class UrlService {

    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public Set<Url> findAll() {
        return repository.findAll();
    }

    public Optional<Url> findByCode(String code) {
        return repository.findByCode(code);
    }

    public Url save(Url url) {
        return repository.save(url);
    }

    public Optional<Url> findById(int id) {
        return repository.findById(id);
    }

    public void delete(Url url) {
        repository.delete(url);
    }

    public void incrementTotal(String code) {
        repository.incrementTotal(code);
    }

    public String generateCode() {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int codeLength = 7;
        SecureRandom rnd = new SecureRandom();
        StringBuilder code = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            code.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        for (Url el : repository.findAll()) {
            if (code.toString().equals(el.getCode())) {
                generateCode();
            }
        }
        return code.toString();
    }
}
