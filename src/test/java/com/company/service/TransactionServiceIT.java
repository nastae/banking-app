package com.company.service;


import com.company.dto.TransactionDTO;
import com.company.repository.TransactionRepository;
import com.company.repository.UserRepository;
import com.company.repository.entity.TransactionEntity;
import com.company.repository.entity.UserEntity;
import com.company.service.mapping.TransactionMapper;
import com.company.service.mapping.TransactionMapperImpl;
import com.google.common.collect.ImmutableList;
import io.atlassian.fugue.Either;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransactionServiceIT {

    @TestConfiguration
    static class TaskServiceITContextConfiguration {
        @Bean
        public TransactionMapper taskMapper() {
            return new TransactionMapperImpl();
        }

    }

    @Autowired
    private TransactionMapper transactionMapper;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void shouldAddTransaction() {
        Long clientId = 10L;
        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(clientId)).thenReturn(Optional.of(userEntity));

        TransactionEntity transactionEntity = new TransactionEntity();
        when(transactionRepository.save(any())).thenReturn(transactionEntity);

        Either<Object, TransactionDTO> result = transactionService.addTransaction(clientId, new TransactionDTO());
        Assert.assertTrue(result.isRight());
        Assert.assertFalse(result.right().isEmpty());
    }

    @Test
    public void shouldFailAddTransactionAndNotFindClient() {
        Long clientId = 10L;
        when(userRepository.findById(clientId)).thenReturn(Optional.empty());

        TransactionEntity transactionEntity = new TransactionEntity();
        when(transactionRepository.save(any())).thenReturn(transactionEntity);

        Either<Object, TransactionDTO> result = transactionService.addTransaction(clientId, new TransactionDTO());
        Assert.assertTrue(result.isLeft());
        Assert.assertFalse(result.left().isEmpty());
    }

    @Test
    public void shouldReturnAllTasks() {
        Long clientId = 10L;
        TransactionEntity entity1 = new TransactionEntity();
        TransactionEntity entity2 = new TransactionEntity();
        Pageable pageable = new PageRequest(1, 10);
        Mockito.when(transactionRepository.findByUserId(clientId, pageable))
                .thenReturn(new PageImpl<>(ImmutableList.of(entity1, entity2)));

        List<TransactionDTO> tasks = transactionService.getAll(clientId, pageable);
        Assert.assertEquals(2, tasks.size());
    }

    @Test
    public void shouldCountAmount() {
        Long clientId = 10L;
        TransactionEntity entity1 = new TransactionEntity();
        entity1.setAmount(10.0);
        TransactionEntity entity2 = new TransactionEntity();
        entity2.setAmount(20.0);
        Mockito.when(transactionRepository.findByUserId(clientId))
                .thenReturn(ImmutableList.of(entity1, entity2));

        Double amount = transactionService.amount(clientId);
        Assert.assertEquals(30, (double) amount, 0.0);
    }
}
