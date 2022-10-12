package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class StatisticDTO {

    @NotBlank(message = "Url must be not empty")
    private String url;

    private int total;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        StatisticDTO that = (StatisticDTO) o;
        return total == that.total && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, total);
    }

    @Override
    public String toString() {
        return "StatisticDTO{"
                + "url='" + url + '\''
                + ", total=" + total
                + '}';
    }
}
