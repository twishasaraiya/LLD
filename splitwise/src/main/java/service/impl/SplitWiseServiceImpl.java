package service.impl;

import enums.SplitType;
import service.ExpenseManager;
import service.SplitWiseService;
import service.UserManagement;

import java.util.List;

public class SplitWiseServiceImpl implements SplitWiseService {
    private final UserManagement userManagement = new UserManagementImpl();
    private final ExpenseManager expenseManager = new ExpenseManagerImpl();

    @Override
    public void addUser(String userId, String userName, String userEmail, String phoneNumber) {
        userManagement.addUser(userId, userName, userEmail, phoneNumber);
    }

    @Override
    public void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values) {
        expenseManager.addExpense(paidByUser, totalAmount, numParticipants, participants, splitType, values);
    }

    @Override
    public void showBalances() {
        List<String[]> allUserBalances = expenseManager.getAllUserBalances();
        if(allUserBalances.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(String[] balance: allUserBalances){
            System.out.println(userManagement.getUserNameById(balance[0]) + " owes " + userManagement.getUserNameById(balance[1]) + ": " + balance[2]);
        }

    }

    @Override
    public void showBalancesByUserId(String userId) {
        List<String[]> userBalances = expenseManager.getUserBalances(userId);
        if(userBalances.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(String[] balance: userBalances){
            System.out.println(userManagement.getUserNameById(balance[0]) + " owes " + userManagement.getUserNameById(balance[1]) + ": " + balance[2]);
        }
    }
}
