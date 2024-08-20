package ru.gladyshev.springcourse.MyProjectPlusJwt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CarAddDTO {

    @NotBlank(message = "Введите марку авто")
    private String carBrand;

    @NotBlank(message = "Введите модель авто")
    private String carModel;

    @NotBlank(message = "Введите год")
    private String year;


    private int carOwner;

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

    public int getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(int carOwner) {
        this.carOwner = carOwner;
    }
}
