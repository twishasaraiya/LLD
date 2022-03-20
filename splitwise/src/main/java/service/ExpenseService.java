package service;

import model.Expense;
import model.ExpenseType;

import java.util.List;

public class ExpenseService {

    public Expense addExpense(Long initiatedBy, Double amount, List<Long> splitBetweenUsersList, ExpenseType expenseType){
        return new Expense(expenseType, amount, initiatedBy, splitBetweenUsersList);
    }
}
