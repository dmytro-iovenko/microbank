package com.microbank.user.command.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
public class RegisterUserResponse extends BaseResponse{
    private String id;

    public RegisterUserResponse(String message, String id) {
        super(message);
        this.setId(id);
    }
   
}
