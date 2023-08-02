package com.microbank.account.query.queries;

import lombok.Data;

@Data
public class FindAccountByIdQuery implements BaseQuery {
    private String id;
}
