package ru.gladyshev.springcourse.MyProjectPlusJwt.dto;

import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Car;

import java.util.List;

public class AccountDTO {

    private String username;

    private String telephone;

    private String email;

    private List<Car> cars;

    public AccountDTO() {
    }

    public AccountDTO(String username, String telephone, String email, List<Car> cars) {
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.cars = cars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
