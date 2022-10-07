package ru.job4j.shortcut.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;
    @NotBlank(message = "Title must be not empty")
    private String username;
    @NotBlank(message = "Title must be not empty")
    private String login;
    @NotBlank(message = "Title must be not empty")
    private String password;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
    private Set<Url> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return username;
    }

    public void setSite(String site) {
        this.username = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Url> getUrls() {
        return urls;
    }

    public void setUrls(Set<Url> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id
                && Objects.equals(username, person.username)
                && Objects.equals(login, person.login)
                && Objects.equals(password, person.password)
                && Objects.equals(urls, person.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, login, password, urls);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", urls=" + urls
                + '}';
    }
}
