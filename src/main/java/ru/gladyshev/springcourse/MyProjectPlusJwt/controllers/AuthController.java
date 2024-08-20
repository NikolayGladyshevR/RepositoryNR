package ru.gladyshev.springcourse.MyProjectPlusJwt.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.AuthDTO.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.RefreshToken;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.UserDetailsImpl;
import ru.gladyshev.springcourse.MyProjectPlusJwt.security.JwtUtils;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.RefreshTokenService;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.UsersService;
import ru.gladyshev.springcourse.MyProjectPlusJwt.utils.TokenRefreshException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final   AuthenticationManager authenticationManager;

    private final  UsersService usersService;


    private final  PasswordEncoder encoder;


    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsersService usersService, PasswordEncoder encoder, RefreshTokenService refreshTokenService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
        this.encoder = encoder;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/signUp")
    public ResponseEntity<MessageResponse> registration(@RequestBody @Valid RegistrationRequest registrationRequest, BindingResult bindingResult){
        if(usersService.existsByUsername(registrationRequest.getUsername())){
            throw new RuntimeException("Такой пользователь уже есть!");
        }
        if(usersService.existsByEmail(registrationRequest.getEmail())){
            throw new RuntimeException("Данная почта уже используется!");
        }
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Неверно введенные данные");
        }

        User user = new User(registrationRequest.getUsername(), registrationRequest.getTelephone(), registrationRequest.getEmail(), registrationRequest.getPassword());
        usersService.saveRegistration(user);


        return ResponseEntity.ok(new MessageResponse("Регистрация прошла успешно!"));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult){
        if(!usersService.existsByUsername(loginRequest.getUsername())){
            throw new RuntimeException("такого пользователя не существует!");
        }
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Неверный логин или пароль!");
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity
                .ok(new JwtResponse(jwt,refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getOwner)
                .map(user -> {
                    String token = jwtUtils.generateToken(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
