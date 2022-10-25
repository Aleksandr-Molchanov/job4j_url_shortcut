package ru.job4j.shortcut.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CodeDTO {

    @NotBlank(message = "Code must be not empty")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeDTO that = (CodeDTO) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "RespRegistrationUrlDTO{"
                + "code='" + code + '\''
                + '}';
    }
}
