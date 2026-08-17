package dev.henrique.example.bankbalance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "owner", "balance", "updated_at" })
public record BalanceResponse(
        String id,
        String owner,
        BalanceData balance,
        @JsonProperty("updated_at") String updatedAt
) {}