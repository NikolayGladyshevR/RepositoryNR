package ru.gladyshev.springcourse.MyProjectPlusJwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByCarOwner_Username(String username);

    Optional<List<Car>> findAllByCarOwner(int id);
}
