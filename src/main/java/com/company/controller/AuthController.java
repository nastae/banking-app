package com.company.controller;

import com.company.dto.UserDTO;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO)
                          .map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }
}
