package dev.henrique.example.bankbalance.dto;

import java.math.BigDecimal;

public record TransactionMessage(
        TransactionData transaction,
        AccountData account
) {}