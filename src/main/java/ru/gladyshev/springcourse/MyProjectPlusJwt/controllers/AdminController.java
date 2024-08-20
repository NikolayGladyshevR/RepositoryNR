package ru.gladyshev.springcourse.MyProjectPlusJwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.MessageResponse;
import ru.gladyshev.springcourse.MyProjectPlusJwt.dto.UsernameDTO;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.ERole;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.Role;
import ru.gladyshev.springcourse.MyProjectPlusJwt.models.User;
import ru.gladyshev.springcourse.MyProjectPlusJwt.services.UsersService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PatchMapping("/users/role")
    public ResponseEntity<MessageResponse> updateUserToRoleAdmin(@RequestBody @Valid UsernameDTO usernameDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Либо некорректно введено имя пользователя, либо его не существует.");
        }

       User user = usersService.findOptionalByUsername(usernameDTO.getUsername()).get();
        user.getRoles().add(new Role(ERole.ROLE_ADMIN));
        usersService.save(user);

      return  ResponseEntity.ok(new MessageResponse("Роль успешно добавлена"));
    }

    @PatchMapping("/users/remove/role")
    public ResponseEntity<MessageResponse> removeUserRoleAdmin(@RequestBody @Valid UsernameDTO usernameDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Либо некорректно введено имя пользователя, либо его не существует.");
        }

        User user = usersService.findOptionalByUsername(usernameDTO.getUsername()).get();
        user.getRoles().removeIf(role -> role.getName().equals(ERole.ROLE_ADMIN));
        usersService.save(user);

        return ResponseEntity.ok(new MessageResponse("Пользователь " + usernameDTO.getUsername() + " ограничен в правах." ));

    }


}
