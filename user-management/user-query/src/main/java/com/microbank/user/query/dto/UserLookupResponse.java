package com.microbank.user.query.dto;

import com.microbank.user.core.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLookupResponse {
    private User user;
}
