package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UrlDTO {

    @NotBlank(message = "Url must be not empty")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlDTO that = (UrlDTO) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "ReqRegistrationUrlDTO{"
                + "url='" + url + '\''
                + '}';
    }
}
