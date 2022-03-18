package service;

import model.Expense;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManagerImpl implements ExpenseManager{

    private final Map<User, Map<User, Double>> balanceMap = new HashMap<>();

    @Override
    public void addExpense(Expense expense) {
        if(!balanceMap.containsKey(expense.getPaidBy())){
            balanceMap.put(expense.getPaidBy(), new HashMap<>());
        }
        double[] amountDistribution = expense.getDistributionStrategy().getAmountDistribution();
        for(int i=0; i<expense.getParticipants().size(); i++){
            if(expense.getPaidBy() == expense.getParticipants().get(i)) continue;
            balanceMap.get(expense.getPaidBy()).put(expense.getParticipants().get(i), balanceMap.get(expense.getPaidBy()).getOrDefault(expense.getParticipants().get(i), 0.0) + amountDistribution[i]);
            if(!balanceMap.containsKey(expense.getParticipants().get(i))){
                balanceMap.put(expense.getParticipants().get(i), new HashMap<>());
            }
            balanceMap.get(expense.getParticipants().get(i)).put(expense.getPaidBy(), balanceMap.get(expense.getParticipants().get(i)).getOrDefault(expense.getPaidBy(), 0.0) - amountDistribution[i]);
        }
    }

    @Override
    public void showAllUserBalances() {
        if(balanceMap.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(User user: balanceMap.keySet()){
            Map<User, Double> userBalance = balanceMap.get(user);
            for(Map.Entry<User, Double> entry: userBalance.entrySet()){
                if(entry.getValue() < 0){
                    System.out.println(user.getUserName() + " owes " + entry.getKey().getUserName() + ": " + -entry.getValue());
                }
            }
        }
    }

    @Override
    public void showUserBalances(User user) {
        if(!balanceMap.containsKey(user)){
            System.out.println("No balances");
            return;
        }
        Map<User, Double> userBalance = balanceMap.get(user);
        for(Map.Entry<User, Double> entry: userBalance.entrySet()){
            if(entry.getValue() > 0){
                System.out.println(entry.getKey().getUserName() + " owes " + user.getUserName() + ": " + entry.getValue());
            }
            else{
                System.out.println(user.getUserName() + " owes " + entry.getKey().getUserName() + ": " + -entry.getValue());
            }
        }
    }

//    @Override
//    public void showGroupBalances(String groupId) {
//
//    }

}
