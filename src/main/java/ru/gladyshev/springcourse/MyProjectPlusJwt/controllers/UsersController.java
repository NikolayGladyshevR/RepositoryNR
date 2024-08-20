package ru.gladyshev.springcourse.MyProjectPlusJwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.AccountDTO;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.MessageResponse;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.security.JwtUtils;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.UsersService;

import java.util.Optional;

@RestController
@RequestMapping("/api/san4ez_garage/users")
public class UsersController {
    private final UsersService usersService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UsersController(UsersService usersService, JwtUtils jwtUtils) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/startPage")
    public ResponseEntity<MessageResponse> start(){
        return ResponseEntity.ok(new MessageResponse("Это стартовая страница для авторизованных пользователей"));
    }


    @GetMapping("/account")
    public ResponseEntity<AccountDTO> account(@RequestHeader(name = "Authorization") String authHeader){
        String accessToken = authHeader.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(accessToken);
        User user = usersService.findOptionalByUsername(username).get();

        AccountDTO accountDTO = new AccountDTO(user.getUsername(),user.getTelephone(),user.getEmail(),user.getCar());

        return ResponseEntity.ok(accountDTO);
    }









}
