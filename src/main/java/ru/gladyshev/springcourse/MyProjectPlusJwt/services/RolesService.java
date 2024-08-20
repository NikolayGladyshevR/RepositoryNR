package ru.gladyshev.springcourse.MyProjectPlusJwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.ERole;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Role;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.RoleRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RolesService {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(ERole name){
        return Optional.ofNullable(roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Нет такой роли")));
    }
}
