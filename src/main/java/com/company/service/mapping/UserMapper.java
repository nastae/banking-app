package com.company.service.mapping;

import com.company.dto.UserDTO;
import com.company.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO fromEntity(UserEntity entity);
    UserEntity fromUser(UserDTO user);
}

