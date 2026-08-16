package dev.henrique.example.bankbalance.dto;

import java.math.BigDecimal;

public record TransactionData(
        String id,
        String type,
        BigDecimal amount,
        String currency,
        String status,
        long timestamp
) {}