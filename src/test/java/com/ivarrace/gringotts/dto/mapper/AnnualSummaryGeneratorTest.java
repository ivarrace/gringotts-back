package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AnnualSummaryGeneratorTest {

    private AnnualSummaryGenerator annualSummaryGenerator;

    @BeforeEach
    void setUp() {
        annualSummaryGenerator = new AnnualSummaryGenerator();
    }

    @Test
    void generateAnnualSummary_empty() {
        AccountingResponse accountingResponse = new AccountingResponse();
        accountingResponse.setExpenses(new ReportResponse(Collections.emptyList()));
        accountingResponse.setIncome(new ReportResponse(Collections.emptyList()));

        annualSummaryGenerator.generateAnnualSummary(accountingResponse);

        RecordsSummary expenses = accountingResponse.getExpenses().get().getAnnualTotals();
        assertNotNull(expenses);
        assertEquals(0, expenses.getTotal());
        assertEquals(0, expenses.getAverage());
        expenses.getMonthly().forEach((month, aDouble) -> assertEquals(0, aDouble));

        RecordsSummary income = accountingResponse.getIncome().get().getAnnualTotals();
        assertNotNull(income);
        assertEquals(0, income.getTotal());
        assertEquals(0, income.getAverage());
        income.getMonthly().forEach((month, aDouble) -> assertEquals(0, aDouble));

        RecordsSummary savings = accountingResponse.getSavings();
        assertNotNull(savings);
        assertEquals(0, savings.getTotal());
        assertEquals(0, savings.getAverage());
        savings.getMonthly().forEach((month, aDouble) -> assertEquals(0, aDouble));
    }

    @Test
    void generateAnnualSummary_sum_same_month() {
        RecordResponse record1 = new RecordResponse();
        record1.setAmount(42);
        record1.setDate(LocalDate.of(1997, Month.JANUARY, 1));
        RecordResponse record2 = new RecordResponse();
        record2.setAmount(13.00);
        record2.setDate(LocalDate.of(1997, Month.JANUARY, 1));
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setRecords(Arrays.asList(record1, record2));
        GroupResponse expense = new GroupResponse();
        expense.setCategories(Collections.singletonList(categoryResponse));
        ReportResponse expenseReport = new ReportResponse(Collections.singletonList(expense));
        AccountingResponse accountingResponse = new AccountingResponse();
        accountingResponse.setExpenses(expenseReport);
        accountingResponse.setIncome(new ReportResponse(Collections.emptyList()));


        annualSummaryGenerator.generateAnnualSummary(accountingResponse);
        RecordsSummary expenses = accountingResponse.getExpenses().get().getAnnualTotals();
        assertNotNull(expenses);
        assertEquals(55, expenses.getTotal());
        assertEquals(4.58, expenses.getAverage());
        expenses.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(55, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });

        RecordsSummary income = accountingResponse.getIncome().get().getAnnualTotals();
        assertNotNull(income);
        assertEquals(0, income.getTotal());
        assertEquals(0, income.getAverage());
        income.getMonthly().forEach((month, aDouble) -> assertEquals(0, aDouble));

        RecordsSummary savings = accountingResponse.getSavings();
        assertNotNull(savings);
        assertEquals(-55, savings.getTotal());
        assertEquals(-4.58, savings.getAverage());
        savings.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(-55, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });
    }

    @Test
    void generateAnnualSummary_sum_distinct_month() {
        RecordResponse record1 = new RecordResponse();
        record1.setAmount(42);
        record1.setDate(LocalDate.of(1997, Month.JANUARY, 1));
        RecordResponse record2 = new RecordResponse();
        record2.setAmount(13.00);
        record2.setDate(LocalDate.of(1997, Month.FEBRUARY, 1));
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setRecords(Arrays.asList(record1, record2));
        GroupResponse expense = new GroupResponse();
        expense.setCategories(Collections.singletonList(categoryResponse));
        ReportResponse expenseReport = new ReportResponse(Collections.singletonList(expense));
        AccountingResponse accountingResponse = new AccountingResponse();
        accountingResponse.setExpenses(expenseReport);
        accountingResponse.setIncome(new ReportResponse(Collections.emptyList()));


        annualSummaryGenerator.generateAnnualSummary(accountingResponse);
        RecordsSummary expenses = accountingResponse.getExpenses().get().getAnnualTotals();
        assertNotNull(expenses);
        assertEquals(55, expenses.getTotal());
        assertEquals(4.58, expenses.getAverage());
        expenses.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(42, aDouble);
            } else if(Month.FEBRUARY.equals(month)){
                assertEquals(13, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });

        RecordsSummary income = accountingResponse.getIncome().get().getAnnualTotals();
        assertNotNull(income);
        assertEquals(0, income.getTotal());
        assertEquals(0, income.getAverage());
        income.getMonthly().forEach((month, aDouble) -> assertEquals(0, aDouble));

        RecordsSummary savings = accountingResponse.getSavings();
        assertNotNull(savings);
        assertEquals(-55, savings.getTotal());
        assertEquals(-4.58, savings.getAverage());
        savings.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(-42, aDouble);
            } else if(Month.FEBRUARY.equals(month)){
                assertEquals(-13, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });
    }

    @Test
    void generateAnnualSummary_sum_same_month_expenses_income() {
        RecordResponse record1 = new RecordResponse();
        record1.setAmount(42);
        record1.setDate(LocalDate.of(1997, Month.JANUARY, 1));
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setRecords(Collections.singletonList(record1));
        GroupResponse expense = new GroupResponse();
        expense.setCategories(Collections.singletonList(categoryResponse));
        ReportResponse expenseReport = new ReportResponse(Collections.singletonList(expense));
        AccountingResponse accountingResponse = new AccountingResponse();
        accountingResponse.setExpenses(expenseReport);
        RecordResponse record2 = new RecordResponse();
        record2.setAmount(12.00);
        record2.setDate(LocalDate.of(1997, Month.JANUARY, 1));
        CategoryResponse categoryResponse2 = new CategoryResponse();
        categoryResponse2.setRecords(Collections.singletonList(record2));
        GroupResponse income = new GroupResponse();
        income.setCategories(Collections.singletonList(categoryResponse2));
        ReportResponse incomeReport = new ReportResponse(Collections.singletonList(income));
        accountingResponse.setExpenses(expenseReport);
        accountingResponse.setIncome(incomeReport);

        annualSummaryGenerator.generateAnnualSummary(accountingResponse);
        RecordsSummary finalExpenses = accountingResponse.getExpenses().get().getAnnualTotals();
        assertNotNull(finalExpenses);
        assertEquals(42, finalExpenses.getTotal());
        assertEquals(3.5, finalExpenses.getAverage());
        finalExpenses.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(42, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });

        RecordsSummary finalIncome = accountingResponse.getIncome().get().getAnnualTotals();
        assertNotNull(finalIncome);
        assertEquals(12, finalIncome.getTotal());
        assertEquals(1, finalIncome.getAverage());
        finalIncome.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(12, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });

        RecordsSummary savings = accountingResponse.getSavings();
        assertNotNull(savings);
        assertEquals(-30, savings.getTotal());
        assertEquals(-2.5, savings.getAverage());
        savings.getMonthly().forEach((month, aDouble) -> {
            if(Month.JANUARY.equals(month)){
                assertEquals(-30, aDouble);
            } else {
                assertEquals(0, aDouble);
            }
        });
    }
}