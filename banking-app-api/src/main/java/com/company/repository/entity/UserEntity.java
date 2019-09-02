package com.company.repository.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_entity")
public class UserEntity {

    public UserEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_user_id_seq")
    @Column(name = "unique_user_id", updatable = false, nullable = false)
    private Long id;

    private String email;

    @Column(length = 64)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<TransactionEntity> transactions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
