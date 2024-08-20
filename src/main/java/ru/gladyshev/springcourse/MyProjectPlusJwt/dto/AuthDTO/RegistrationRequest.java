package ru.gladyshev.springcourse.MyProjectPlusJwt.dto.AuthDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotBlank
    @Size(min = 5, max = 25, message = "имя пользователя должно быть от 5 до 25 символов")
    private String username;

    @NotBlank
    @Size(min = 5, max=15, message = "Пароль должен содержать от 5 до 15 символов")
    private String password;

    @NotBlank
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
    private String telephone;

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;


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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
