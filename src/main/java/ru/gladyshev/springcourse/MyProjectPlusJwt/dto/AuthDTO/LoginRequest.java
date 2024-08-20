package ru.gladyshev.springcourse.MyProjectPlusJwt.dto.AuthDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotBlank
    @Size(min = 5, max = 25, message = "имя пользователя должно быть от 5 до 25 символов")
    private String username;

    @NotBlank
    @Size(min = 5, max=15, message = "Пароль должен содержать от 5 до 15 символов")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
