package service;

import enums.SplitType;
import java.util.List;

public interface ExpenseManager {
    void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values);
    void showAllUserBalances();
    void showUserBalances(String userId);

}
