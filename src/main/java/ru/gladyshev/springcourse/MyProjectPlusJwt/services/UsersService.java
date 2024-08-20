package ru.gladyshev.springcourse.MyProjectPlusJwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.ERole;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Role;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.UserRepository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.utils.NoFoundUserException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username){
      return  userRepository.findByUsername(username);
    }

    public Optional<User> findOptionalByUsername(String username){
        return Optional.ofNullable(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден")));
    }

    public Optional<User> findUserById(int id){
        return Optional.ofNullable(userRepository
                .findById(id).orElseThrow(() -> new NoFoundUserException("Пользователя с таком id не существует!")));
    }

    @Transactional
    public void saveRegistration(User user){
        enrichUser(user);

        userRepository.save(user);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }


    public boolean existsByUsername(String username){
       return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByUsername(email);
    }


    public void enrichUser(User user){
        ERole erole = ERole.ROLE_USER;
        Role role = new Role(erole);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountStatus(true);
    }
}
