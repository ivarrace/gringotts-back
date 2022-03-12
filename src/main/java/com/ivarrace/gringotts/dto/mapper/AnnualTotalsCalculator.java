package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.response.*;

public class AnnualTotalsCalculator {

    public void generateAnnualTotals(AccountingResponse accounting) {
        if (accounting.getExpenses() != null) {
            accounting.getExpenses().setAnnualTotals(getReportAnnualTotals(accounting.getExpenses()));
        }
        if (accounting.getIncome() != null) {
            accounting.getIncome().setAnnualTotals(getReportAnnualTotals(accounting.getIncome()));
        }
        accounting.setSavings(getAccountingSavings(accounting));
    }

    private RecordsSummary getAccountingSavings(AccountingResponse accounting) {
        RecordsSummary recordsSummary = new RecordsSummary();
        double total = 0;
        if (accounting.getExpenses() != null) {
            accounting.getExpenses().getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, -amount, Double::sum)
            );
            total -= accounting.getExpenses().getAnnualTotals().getTotal();
        }
        if (accounting.getIncome() != null) {
            accounting.getIncome().getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            total += accounting.getIncome().getAnnualTotals().getTotal();
        }
        recordsSummary.setTotal(total);
        recordsSummary.setAverage(total / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getReportAnnualTotals(ReportResponse reportResponse) {
        RecordsSummary recordsSummary = new RecordsSummary();
        reportResponse.getGroups().forEach(group -> {
            group.setAnnualTotals(getGroupAnnualTotals(group));
            group.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            recordsSummary.setTotal(recordsSummary.getTotal() + group.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getGroupAnnualTotals(GroupResponse group) {
        RecordsSummary recordsSummary = new RecordsSummary();
        group.getCategories().forEach(category -> {
            category.setAnnualTotals(getCategoryAnnualTotals(category));
            category.getAnnualTotals().getMonthly().forEach((month, amount) ->
                    recordsSummary.getMonthly().merge(month, amount, Double::sum)
            );
            recordsSummary.setTotal(recordsSummary.getTotal() + category.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getCategoryAnnualTotals(CategoryResponse category) {
        RecordsSummary recordsSummary = new RecordsSummary();
        category.getRecords().forEach(item -> {
            recordsSummary.getMonthly().merge(item.getDate().getMonth(), item.getAmount(), Double::sum);
            recordsSummary.setTotal(recordsSummary.getTotal() + item.getAmount());
        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

}
