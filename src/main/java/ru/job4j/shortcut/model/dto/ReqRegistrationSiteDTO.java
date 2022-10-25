package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ReqRegistrationSiteDTO {

    @NotBlank(message = "Site must be not empty")
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReqRegistrationSiteDTO that = (ReqRegistrationSiteDTO) o;
        return Objects.equals(site, that.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site);
    }

    @Override
    public String toString() {
        return "ReqRegistrationDTO{"
                + "site='" + site + '\''
                + '}';
    }
}
