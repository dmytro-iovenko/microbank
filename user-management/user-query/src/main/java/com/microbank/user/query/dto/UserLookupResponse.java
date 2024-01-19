package com.microbank.user.query.dto;

import java.util.List;

import com.microbank.user.core.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLookupResponse {
    private List<User> users;
}
