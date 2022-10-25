package ru.job4j.shortcut.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null")
    private int id;
    @NotBlank(message = "Url must be not empty")
    private String url;
    @NotBlank(message = "Code must be not empty")
    private String code;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url1 = (Url) o;
        return id == url1.id
                && total == url1.total
                && Objects.equals(url, url1.url)
                && Objects.equals(code, url1.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, code, total);
    }

    @Override
    public String toString() {
        return "Url{"
                + "id=" + id
                + ", url='" + url + '\''
                + ", code='" + code + '\''
                + ", total=" + total
                + '}';
    }
}
