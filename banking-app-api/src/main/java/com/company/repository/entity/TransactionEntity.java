package com.company.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction_entity")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unique_transaction_id_seq")
    @Column(name = "unique_transaction_id", updatable = false, nullable = false)
    private Long id;

    @Column(scale = 2)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
