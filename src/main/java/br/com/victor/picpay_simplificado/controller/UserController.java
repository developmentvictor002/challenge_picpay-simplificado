package br.com.victor.picpay_simplificado.controller;

import br.com.victor.picpay_simplificado.dto.UserRequestDto;
import br.com.victor.picpay_simplificado.dto.UserResponseDto;
import br.com.victor.picpay_simplificado.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
        UserResponseDto responseDto = userService.createUser(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.userId())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") UUID userId) {
        UserResponseDto responseDto = userService.getUserById(userId);
        return ResponseEntity.ok(responseDto);
    }
}
