package ru.job4j.shortcut.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;
    @NotBlank(message = "Domain must be not empty")
    private String domain;
    @NotBlank(message = "Login must be not empty")
    private String login;
    @NotBlank(message = "Password must be not empty")
    private String password;
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "site_id", foreignKey = @ForeignKey(name = "SITE_ID_FK"))
    private Set<Url> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String site) {
        this.domain = site;
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
        Site site = (Site) o;
        return id == site.id
                && Objects.equals(domain, site.domain)
                && Objects.equals(login, site.login)
                && Objects.equals(password, site.password)
                && Objects.equals(urls, site.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, login, password, urls);
    }

    @Override
    public String toString() {
        return "Site{"
                + "id=" + id
                + ", username='" + domain + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", urls=" + urls
                + '}';
    }
}
