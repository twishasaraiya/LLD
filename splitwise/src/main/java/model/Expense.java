package model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Expense {
    private UUID id;
    private Double amount;
    private ExpenseType expenseType;
    private Long initiatedBy;
    private List<Long> splitBetweenUsers;
    private Optional<List<Double>> additionalDataList;

    public Expense(ExpenseType expenseType, Double amount, Long initiatedBy, List<Long> splitBetweenUsers, Optional<List<Double>> additionalDataList) {
        this.id = UUID.randomUUID();
        this.expenseType = expenseType;
        this.initiatedBy = initiatedBy;
        this.amount = amount;
        this.splitBetweenUsers = splitBetweenUsers;
        this.additionalDataList = additionalDataList;
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

    public Optional<List<Double>> getAdditionalDataList() {
        return additionalDataList;
    }
}
