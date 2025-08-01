package ru.gladyshev.springcourse.MyProjectPlusJwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.UserDetailsImpl;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


   private final UserRepository userRepository;

   @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        return UserDetailsImpl.build(user);
    }
}
