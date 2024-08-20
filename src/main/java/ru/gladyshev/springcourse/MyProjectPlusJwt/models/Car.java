package ru.gladyshev.springcourse.MyProjectPlusJwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    @NotBlank(message = "Введите марку авто")
    private String carBrand;

    @Column(name = "model")
    @NotBlank(message = "Введите модель авто")
    private String carModel;

    @Column(name = "year")
    @NotBlank(message = "Введите год")
    private String year;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "carowner_id", referencedColumnName = "id")
    private User carOwner;

    public Car() {
    }

    public Car(String carBrand, String carModel, String year, User carOwner) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.year = year;
        this.carOwner = carOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(User carOwner) {
        this.carOwner = carOwner;
    }
}
