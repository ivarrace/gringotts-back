package com.ivarrace.gringotts.domain.dto;

import lombok.Data;

@Data
public class UserAccountingRoleDto {

    private String userId;
    private AccountingRoleDto role;

}