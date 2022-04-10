package service;

import enums.SplitType;

import java.util.List;

public interface SplitWiseService {
    void addUser(String userId, String userName, String userEmail, String phoneNumber);
    void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values);
    void showBalances();
    void showBalancesByUserId(String userId);

}
