package model;

import enums.SplitType;

import java.util.List;
import java.util.UUID;

public class Expense {
    private String expenseId;
    private ExpenseMetaData expenseMetaData;
    private String paidBy;
    private Double expenseAmount;
    private List<String> participants;
    private SplitType splitType;

    public Expense() {
        this.expenseId = UUID.randomUUID().toString();
        this.expenseMetaData = new ExpenseMetaData();
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public ExpenseMetaData getExpenseMetaData() {
        return expenseMetaData;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }
}
