package com.ivarrace.gringotts.application.rest.dto.mapper;

import com.ivarrace.gringotts.application.rest.dto.response.*;

public class AnnualSummaryGenerator {

    private AnnualSummaryGenerator(){
        throw new IllegalStateException("Utility class");
    }

    public static RecordsSummary generateSavingsSummary(AccountingResponse accounting) {
        RecordsSummary recordsSummary = new RecordsSummary();
        double total = 0;
        if(accounting.getExpenses().isPresent()){
            ReportResponse expenses = accounting.getExpenses().get();
            expenses.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, -amount, Double::sum)
            );
            total -= expenses.getAnnualTotals().getTotal();
        }
        if(accounting.getIncome().isPresent()){
            ReportResponse income = accounting.getIncome().get();
            income.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            total += income.getAnnualTotals().getTotal();
        }
        recordsSummary.setTotal(total);
        recordsSummary.setAverage(total / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    public static RecordsSummary generateReportAnnualSummary(ReportResponse reportResponse) {
        RecordsSummary recordsSummary = new RecordsSummary();
        reportResponse.getGroups().forEach(group -> {
            group.setAnnualTotals(generateGroupAnnualSummary(group));
            group.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            recordsSummary.setTotal(recordsSummary.getTotal() + group.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    public static RecordsSummary generateGroupAnnualSummary(GroupResponse group) {
        RecordsSummary recordsSummary = new RecordsSummary();
        group.getCategories().forEach(category -> {
            category.setAnnualTotals(generateCategoryAnnualSummary(category));
            category.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            recordsSummary.setTotal(recordsSummary.getTotal() + category.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    public static RecordsSummary generateCategoryAnnualSummary(CategoryResponse category) {
        RecordsSummary recordsSummary = new RecordsSummary();
        category.getRecords().forEach(item -> {
            recordsSummary.getMonthly().merge(item.getDate().getMonth(), item.getAmount(), Double::sum);
            recordsSummary.setTotal(recordsSummary.getTotal() + item.getAmount());
        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

}
