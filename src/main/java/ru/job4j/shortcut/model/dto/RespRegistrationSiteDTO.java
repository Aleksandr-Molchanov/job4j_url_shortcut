package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RespRegistrationSiteDTO {

    private boolean registration;

    @NotBlank(message = "Login must be not empty")
    private String login;

    @NotBlank(message = "Password must be not empty")
    private String password;

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RespRegistrationSiteDTO that = (RespRegistrationSiteDTO) o;
        return registration == that.registration
                && Objects.equals(login, that.login)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, login, password);
    }

    @Override
    public String toString() {
        return "RespRegistrationDTO{"
                + "registration=" + registration
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
