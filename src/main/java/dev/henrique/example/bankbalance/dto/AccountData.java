package dev.henrique.example.bankbalance.dto;

public record AccountData(
        String id,
        String owner,
        String status,
        BalanceData balance
) {}

