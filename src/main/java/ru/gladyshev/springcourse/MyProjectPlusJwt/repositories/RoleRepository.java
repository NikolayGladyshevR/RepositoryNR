package ru.gladyshev.springcourse.MyProjectPlusJwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.ERole;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName (ERole name);
}
