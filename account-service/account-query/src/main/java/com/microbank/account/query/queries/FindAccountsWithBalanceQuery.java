package com.microbank.account.query.queries;

import com.microbank.account.query.dto.EqualityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountsWithBalanceQuery implements BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
