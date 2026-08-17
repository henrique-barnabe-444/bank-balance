package dev.henrique.example.bankbalance.service;

import dev.henrique.example.bankbalance.dto.TransactionMessage;
import dev.henrique.example.bankbalance.model.AccountBalance;
import dev.henrique.example.bankbalance.repository.AccountBalanceRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class SqsConsumerService {

    private final AccountBalanceRepository repository;

    public SqsConsumerService(AccountBalanceRepository repository) {
        this.repository = repository;
    }

    @SqsListener("transacoes-financeiras-processadas")
    public void listen(TransactionMessage message) {
        if (message == null || message.account() == null) return;

        System.out.println("Received transaction for account: " + message.account().id() + " | Amount: " + message.transaction().amount());

        String accountId = message.account().id();
        String ownerId = message.account().owner();
        var balance = message.account().balance();

        // Convert epoch timestamp (microseconds) to OffsetDateTime
        long epochMicro = message.transaction().timestamp();
        var updatedAt = Instant.ofEpochMilli(epochMicro / 1000)
                .atOffset(ZoneOffset.ofHours(-3));

        // Save or update balance in DB
        AccountBalance entity = new AccountBalance(
                accountId, ownerId, balance.amount(), balance.currency(), updatedAt
        );

        repository.save(entity);
    }
}