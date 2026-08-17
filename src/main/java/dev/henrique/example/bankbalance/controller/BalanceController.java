package dev.henrique.example.bankbalance.controller;

import dev.henrique.example.bankbalance.dto.BalanceData;
import dev.henrique.example.bankbalance.dto.BalanceResponse;
import dev.henrique.example.bankbalance.repository.AccountBalanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final AccountBalanceRepository repository;

    public BalanceController(AccountBalanceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountId) {
        return repository.findById(accountId)
                .map(account -> {
                    // 1. Create the nested balance object using the existing BalanceData DTO
                    BalanceData balanceData = new BalanceData(account.getAmount(), account.getCurrency());

                    // 2. Construct the final response matching the exact requested order
                    BalanceResponse response = new BalanceResponse(
                            account.getAccountId(),
                            account.getOwnerId(),
                            balanceData,
                            account.getUpdatedAt().toString()
                    );

                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}