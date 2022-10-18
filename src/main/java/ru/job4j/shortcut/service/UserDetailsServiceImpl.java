package ru.job4j.shortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private SiteRepository repository;

    public UserDetailsServiceImpl(SiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String domain) throws UsernameNotFoundException {
        Optional<Site> user = repository.findByDomain(domain);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(domain);
        }
        return new User(user.get().getLogin(), user.get().getPassword(), emptyList());
    }
}