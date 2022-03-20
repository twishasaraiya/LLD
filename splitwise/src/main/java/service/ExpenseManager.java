package service;

import enums.SplitType;
import java.util.List;

public interface ExpenseManager {
    void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values);
    List<String[]> getAllUserBalances();
    List<String[]> getUserBalances(String userId);

}
