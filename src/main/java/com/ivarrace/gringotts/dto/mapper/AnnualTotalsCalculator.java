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
            accounting.getExpenses().getAnnualTotals().getMonthly().forEach((month, ammount) -> {
                Double actual = recordsSummary.getMonthly().get(month);
                recordsSummary.getMonthly().put(month, actual - ammount);
            });
            total -= accounting.getExpenses().getAnnualTotals().getTotal();
        }
        if (accounting.getIncome() != null) {
            accounting.getIncome().getAnnualTotals().getMonthly().forEach((month, ammount) -> {
                Double actual = recordsSummary.getMonthly().get(month);
                recordsSummary.getMonthly().put(month, actual + ammount);
            });
            total += accounting.getIncome().getAnnualTotals().getTotal();
        }
        recordsSummary.setTotal(total);
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getReportAnnualTotals(ReportResponse reportResponse) {
        RecordsSummary recordsSummary = new RecordsSummary();
        reportResponse.getGroups().forEach(group -> {
            group.setAnnualTotals(getGroupAnnualTotals(group));
            group.getAnnualTotals().getMonthly().forEach((month, amount) -> {
                Double actual = recordsSummary.getMonthly().get(month);
                recordsSummary.getMonthly().put(month, actual + amount);
            });
            double actual = recordsSummary.getTotal();
            recordsSummary.setTotal(actual + group.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getGroupAnnualTotals(GroupResponse group) {
        RecordsSummary recordsSummary = new RecordsSummary();
        group.getCategories().forEach(category -> {
            category.setAnnualTotals(getCategoryAnnualTotals(category));
            category.getAnnualTotals().getMonthly().forEach((month, ammount) -> {
                Double actual = recordsSummary.getMonthly().get(month);
                recordsSummary.getMonthly().put(month, actual + ammount);
            });
            double actual = recordsSummary.getTotal();
            recordsSummary.setTotal(actual + category.getAnnualTotals().getTotal());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

    private RecordsSummary getCategoryAnnualTotals(CategoryResponse category) {
        RecordsSummary recordsSummary = new RecordsSummary();
        category.getRecords().forEach(item -> {
            Double actual = recordsSummary.getMonthly().get(item.getDate().getMonth());
            recordsSummary.getMonthly().put(item.getDate().getMonth(), actual + item.getAmount());
            recordsSummary.setTotal(recordsSummary.getTotal() + item.getAmount());

        });
        recordsSummary.setAverage(recordsSummary.getTotal() / recordsSummary.getMonthly().keySet().size());
        return recordsSummary;
    }

}
