package ru.gladyshev.springcourse.MyProjectPlusJwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     User findByUsername(String username);

     Optional<User> findUserByUsername(String username);



     boolean existsByUsername(String username);

     boolean existsByEmail(String email);


}
