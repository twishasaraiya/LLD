package service;

import model.Expense;
import model.User;

public interface ExpenseManager {

//    void addGroupExpense(String groupId, Expense expense);
    void addExpense(Expense expense);
    void showAllUserBalances();
    void showUserBalances(User user);
//    void showGroupBalances(String groupId);
}
