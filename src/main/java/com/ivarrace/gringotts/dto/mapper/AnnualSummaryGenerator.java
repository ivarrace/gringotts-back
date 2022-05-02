package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.response.*;

public class AnnualSummaryGenerator {

    public void generateAnnualSummary(AccountingResponse accounting) {
        accounting.getExpenses().ifPresent(
                expenses -> expenses.setAnnualTotals(generateReportAnnualSummary(expenses))
        );
        accounting.getIncome().ifPresent(
                income -> income.setAnnualTotals(generateReportAnnualSummary(income))
        );
        accounting.setSavings(generateSavingsSummary(accounting));
    }

    private RecordsSummary generateSavingsSummary(AccountingResponse accounting) {
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

    private RecordsSummary generateReportAnnualSummary(ReportResponse reportResponse) {
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

    private RecordsSummary generateGroupAnnualSummary(GroupResponse group) {
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

    private RecordsSummary generateCategoryAnnualSummary(CategoryResponse category) {
        RecordsSummary recordsSummary = new RecordsSummary();
        category.getRecords().forEach(item -> {
            recordsSummary.getMonthly().merge(item.getDate().getMonth(), item.getAmount(), Double::sum);
            recordsSummary.setTotal(recordsSummary.getTotal() + item.getAmount());
        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

}
