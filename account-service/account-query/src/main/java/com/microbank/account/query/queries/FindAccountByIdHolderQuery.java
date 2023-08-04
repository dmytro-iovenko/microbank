package com.microbank.account.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountByIdHolderQuery implements BaseQuery {
    private String accountHolder;
}
