package com.ivarrace.gringotts.application.rest.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class AccountingResponse {

    private String id;
    private String key;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private String name;
    private Optional<ReportResponse> expenses;
    private Optional<ReportResponse> income;
    private RecordsSummary savings;

}
