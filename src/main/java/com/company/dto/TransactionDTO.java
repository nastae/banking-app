package com.company.dto;

import com.company.repository.entity.Direction;

public class TransactionDTO {

    private Double amount;
    private Direction direction;

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
}
