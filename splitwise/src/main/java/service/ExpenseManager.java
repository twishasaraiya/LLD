package service;

import dto.Balance;
import enums.SplitType;
import java.util.List;

public interface ExpenseManager {
    void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values);
    List<Balance> getAllUserBalances();
    List<Balance> getUserBalances(String userId);

}
