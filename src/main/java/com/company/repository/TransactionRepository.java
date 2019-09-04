package com.company.repository;

import com.company.repository.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>,
        CrudRepository<TransactionEntity, Long>,
        PagingAndSortingRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserId(Long id);
    Page<TransactionEntity> findByUserId(Long id, Pageable pageable);
}
