package com.ivarrace.gringotts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserAccountingRole {

    private String userId;
    private AccountingRole role;

}