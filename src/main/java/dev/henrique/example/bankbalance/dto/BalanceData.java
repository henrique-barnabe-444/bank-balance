package dev.henrique.example.bankbalance.dto;

import java.math.BigDecimal;

public record BalanceData(
        BigDecimal amount,
        String currency
) {}
