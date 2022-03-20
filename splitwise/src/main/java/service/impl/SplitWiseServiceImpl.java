package service.impl;

import dto.Balance;
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
        List<Balance> allUserBalances = expenseManager.getAllUserBalances();
        if(allUserBalances.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(Balance balance: allUserBalances){
            System.out.println(userManagement.getUserNameById(balance.getLentBy()) + " owes " + userManagement.getUserNameById(balance.getPaidBy()) + ": " + balance.getAmount());
        }

    }

    @Override
    public void showBalancesByUserId(String userId) {
        List<Balance> userBalances = expenseManager.getUserBalances(userId);
        if(userBalances.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(Balance balance: userBalances){
            System.out.println(userManagement.getUserNameById(balance.getLentBy()) + " owes " + userManagement.getUserNameById(balance.getPaidBy()) + ": " + balance.getAmount());
        }
    }
}
