package dev.henrique.example.bankbalance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
public class AccountBalance {
    @Id
    private String accountId;
    private String ownerId;
    private BigDecimal amount;
    private String currency;
    private OffsetDateTime updatedAt;

    public AccountBalance() {}

    public AccountBalance(String accountId, String ownerId, BigDecimal amount, String currency, OffsetDateTime updatedAt) {
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.amount = amount;
        this.currency = currency;
        this.updatedAt = updatedAt;
    }
}