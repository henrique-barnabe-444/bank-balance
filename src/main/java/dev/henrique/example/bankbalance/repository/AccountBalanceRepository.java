package dev.henrique.example.bankbalance.repository;

import dev.henrique.example.bankbalance.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
}