package ru.gladyshev.springcourse.MyProjectPlusJwt.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.CarAddDTO;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.MessageResponse;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Car;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.CarsService;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.UsersService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/san4ez_garage/cars")
public class CarsController {
    private final CarsService carsService;
    private final ModelMapper modelMapper;

    private final UsersService usersService;

    @Autowired
    public CarsController(CarsService carsService, ModelMapper modelMapper, UsersService usersService) {
        this.carsService = carsService;
        this.modelMapper = modelMapper;
        this.usersService = usersService;
    }

    @PostMapping("/addCar")
    public ResponseEntity<MessageResponse> addCarToUser(@RequestBody @Valid CarAddDTO carAddDTO, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            throw new RuntimeException("Неверно введены данные автомобиля!");
        }
        Car car = new Car();
        car.setCarBrand(carAddDTO.getCarBrand());
        car.setCarModel(carAddDTO.getCarModel());
        car.setYear(carAddDTO.getYear());
        Optional<User> userById = usersService.findUserById(carAddDTO.getCarOwner());

        car.setCarOwner(userById.get());

        carsService.saveCar(car);

        return ResponseEntity.ok(new MessageResponse("Автомобиль успешно сохранен!"));
    }



    public Car convertDtoToCar(CarAddDTO carAddDTO){
        return modelMapper.map(carAddDTO, Car.class);
    }

}
