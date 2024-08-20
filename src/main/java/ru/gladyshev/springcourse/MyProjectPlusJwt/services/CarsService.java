package ru.gladyshev.springcourse.MyProjectPlusJwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Car;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.CarsRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarsService {

    private final CarsRepository carsRepository;

    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Optional<Car> findCarByUsername(String username){
        return Optional.ofNullable(carsRepository
                .findByCarOwner_Username(username).orElseThrow(() -> new RuntimeException("Автомобиль не найден.")));
    }


    public List<Car> carIndex(){
        return carsRepository.findAll();
    }

    public Optional<List<Car>> carsIndexByOwnerUsername(int id){
        return Optional.of(carsRepository.findAllByCarOwner(id).orElse(Collections.EMPTY_LIST));
    }

    @Transactional
    public void saveCar(Car car){
        carsRepository.save(car);
    }

    @Transactional
    public void deleteCar(Car car){
        carsRepository.delete(car);
    }


}
