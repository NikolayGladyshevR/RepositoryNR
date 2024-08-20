package ru.gladyshev.springcourse.MyProjectPlusJwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.RefreshToken;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

   Optional<RefreshToken> findByToken(String token);

   @Modifying
    void deleteByOwner(User user);
}
