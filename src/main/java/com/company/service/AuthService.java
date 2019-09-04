package com.company.service;

import com.company.dto.UserDTO;
import com.company.repository.UserRepository;
import com.company.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Optional<UserDTO> register(UserDTO userDTO) {
        if (emailExist(userDTO.getEmail())) {
            return Optional.empty();
        }
        return Optional.ofNullable(userMapper.fromEntity(userRepository.save(userMapper.fromUser(userDTO))));
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
