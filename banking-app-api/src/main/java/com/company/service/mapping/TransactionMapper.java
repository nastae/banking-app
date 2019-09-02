package com.company.service.mapping;

import com.company.dto.TransactionDTO;
import com.company.repository.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO fromEntity(TransactionEntity entity);
    TransactionEntity fromDto(TransactionDTO transactionDTO);
}
