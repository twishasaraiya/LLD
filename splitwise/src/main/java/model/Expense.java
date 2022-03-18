package model;

import strategy.IDistributionStrategy;

import java.util.List;
import java.util.UUID;

public class Expense {
    private String expenseId;
    private String expenseName;
    private String description;
    private User paidBy;
    private Double expenseAmount;
    private List<User> participants;
    private IDistributionStrategy distributionStrategy;

    public Expense() {
        this.expenseId = UUID.randomUUID().toString();
    }

    public String getExpenseId() {
        return expenseId;
    }


    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public IDistributionStrategy getDistributionStrategy() {
        return distributionStrategy;
    }

    public void setDistributionStrategy(IDistributionStrategy distributionStrategy) {
        this.distributionStrategy = distributionStrategy;
    }
}
