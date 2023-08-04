package com.microbank.account.query.dto;

import java.util.List;

import com.microbank.account.core.dto.BaseResponse;
import com.microbank.account.query.entities.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountLookupResponse extends BaseResponse {
    private List<Account> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }

}
