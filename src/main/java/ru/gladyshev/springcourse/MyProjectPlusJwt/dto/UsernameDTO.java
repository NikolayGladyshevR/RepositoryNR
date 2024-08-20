package ru.gladyshev.springcourse.MyProjectPlusJwt.dto;

import javax.validation.constraints.NotBlank;

public class UsernameDTO {

    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
