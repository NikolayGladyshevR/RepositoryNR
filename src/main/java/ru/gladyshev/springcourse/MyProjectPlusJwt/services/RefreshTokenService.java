package ru.gladyshev.springcourse.MyProjectPlusJwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.RefreshToken;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.RefreshTokenRepository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.repositories.UserRepository;
import ru.gladyshev.springcourse.MyProjectPlusJwt.utils.TokenRefreshException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RefreshTokenService {

    @Value("${gladyshev.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public RefreshToken createRefreshToken(int userId){

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setOwner(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    public Optional<RefreshToken> findByToken(String refreshToken){
       return Optional.ofNullable(refreshTokenRepository.findByToken(refreshToken)
               .orElseThrow(() -> new TokenRefreshException(refreshToken, "Не существует такого токена")));
    }

    @Transactional
    public void deleteByUserId(int userId) {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()){
             refreshTokenRepository.deleteByOwner(byId.get());
        }else throw new RuntimeException("Пользователь с таким id нет, невозможно удалить по этому id пользователя");

    }


}
