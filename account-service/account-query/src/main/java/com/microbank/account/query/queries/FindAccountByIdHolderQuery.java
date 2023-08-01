package com.microbank.account.query.queries;

import lombok.Data;

@Data
public class FindAccountByIdHolderQuery {
    private String accountHolder;
}
