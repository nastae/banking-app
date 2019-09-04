package com.company.service;

import com.company.dto.TransactionDTO;
import com.company.error.Error;
import com.company.repository.TransactionRepository;
import com.company.repository.UserRepository;
import com.company.repository.entity.UserEntity;
import com.company.repository.entity.TransactionEntity;
import com.company.service.mapping.TransactionMapper;
import io.atlassian.fugue.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
    }

    public synchronized Either<Object, TransactionDTO> addTransaction(Long clientId, TransactionDTO transaction) {
        final Optional<UserEntity> user =  userRepository.findById(clientId);
        if (user.isPresent()) {
            final TransactionEntity tx = transactionMapper.fromDto(transaction);
            tx.setUser(user.get());
            return Either.right(transactionMapper.fromEntity(transactionRepository.save(tx)));
        } else {
            return Either.left(Error.CLIENT_NOT_EXIST);
        }
    }

    public List<TransactionDTO> getAll(Long clientId, Pageable pageRequest) {
        return transactionRepository.findByUserId(clientId, pageRequest)
                                    .stream()
                                    .map(transactionMapper::fromEntity)
                                    .collect(toList());
    }

    public Double amount(Long clientId) {
        return transactionRepository.findByUserId(clientId)
                                    .stream()
                                    .map(TransactionEntity::getAmount)
                                    .reduce(0D, Double::sum);
    }
}
