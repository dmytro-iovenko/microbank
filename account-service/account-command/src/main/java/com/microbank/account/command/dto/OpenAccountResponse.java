package com.microbank.account.command.dto;

import com.microbank.account.core.dto.BaseResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class OpenAccountResponse extends BaseResponse{
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.setId(id);
    }
   
}
