package com.ivarrace.gringotts.application.dto.response;

import java.util.Collections;
import java.util.List;

public class ReportResponse {

    private List<GroupResponse> groups;
    private RecordsSummary recordsSummary;

    public ReportResponse(List<GroupResponse> groups) {
        this.groups = groups == null ? Collections.emptyList() : groups;
        this.recordsSummary = new RecordsSummary();
    }

    public List<GroupResponse> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupResponse> groups) {
        this.groups = groups;
    }

    public RecordsSummary getAnnualTotals() {
        return recordsSummary;
    }

    public void setAnnualTotals(RecordsSummary recordsSummary) {
        this.recordsSummary = recordsSummary;
    }

}
