package com.microbank.account.query.queries;

import com.microbank.account.query.dto.EqualityType;

import lombok.Data;

@Data
public class FindAccountsWithBalanceQuery {
    private EqualityType equalityType;
    private double balance;
}
