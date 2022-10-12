package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.shortcut.model.Url;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface UrlRepository extends CrudRepository<Url, Integer> {

    @Override
    Set<Url> findAll();

    Optional<Url> findByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "update Url set total = total + 1 where code = :uCode")
    void incrementTotal(@Param("uCode") String code);
}
