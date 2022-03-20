package model;

import java.util.List;
import java.util.UUID;

public class Expense {
    private UUID id;
    private Double amount;
    private ExpenseType expenseType;
    private Long initiatedBy;
    private List<Long> splitBetweenUsers;

    public Expense(ExpenseType expenseType, Double amount, Long initiatedBy, List<Long> splitBetweenUsers) {
        this.expenseType = expenseType;
        this.initiatedBy = initiatedBy;
        this.amount = amount;
        this.splitBetweenUsers = splitBetweenUsers;
    }

    public UUID getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public Long getInitiatedBy() {
        return initiatedBy;
    }

    public List<Long> getSplitBetweenUsers() {
        return splitBetweenUsers;
    }
}
