package service.impl;

import enums.SplitType;
import model.Expense;
import service.ExpenseManager;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentageSplitStrategy;
import strategy.SplitStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManagerImpl implements ExpenseManager {

    private final List<Expense> expenses = new ArrayList<>();
    private final Map<String, Map<String, Double>> balanceMap = new HashMap<>();

    @Override
    public void addExpense(String paidByUser, double totalAmount, int numParticipants, List<String> participants, SplitType splitType, double[] values) {
        Expense expense = new Expense();
        expense.setPaidBy(paidByUser);
        expense.setExpenseAmount(totalAmount);
        expense.setParticipants(participants);
        expense.setSplitType(splitType);
        expenses.add(expense);
        SplitStrategy splitStrategy = null;
        switch (splitType){
            case EQUAL:
                splitStrategy = new EqualSplitStrategy(totalAmount, numParticipants, values);
                break;
            case EXACT:
                splitStrategy = new ExactSplitStrategy(totalAmount, numParticipants, values);
                break;
            case PERCENT:
                splitStrategy = new PercentageSplitStrategy(totalAmount, numParticipants, values);
        }
        double[] splitAmount = splitStrategy.getSplitAmount();
        if(splitAmount == null){
            System.out.println("Invalid User Input.");
            return;
        }
        if(!balanceMap.containsKey(expense.getPaidBy())){
            balanceMap.put(expense.getPaidBy(), new HashMap<>());
        }
        for(int i=0; i<expense.getParticipants().size(); i++){
            if(expense.getPaidBy().equals(expense.getParticipants().get(i))) continue;
            balanceMap.get(expense.getPaidBy()).put(expense.getParticipants().get(i), balanceMap.get(expense.getPaidBy()).getOrDefault(expense.getParticipants().get(i), 0.0) + splitAmount[i]);
            if(!balanceMap.containsKey(expense.getParticipants().get(i))){
                balanceMap.put(expense.getParticipants().get(i), new HashMap<>());
            }
            balanceMap.get(expense.getParticipants().get(i)).put(expense.getPaidBy(), balanceMap.get(expense.getParticipants().get(i)).getOrDefault(expense.getPaidBy(), 0.0) - splitAmount[i]);
        }

    }

    @Override
    public void showAllUserBalances() {
        if(balanceMap.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(String userId: balanceMap.keySet()){
            Map<String, Double> userBalance = balanceMap.get(userId);
            for(Map.Entry<String, Double> entry: userBalance.entrySet()){
                if(entry.getValue() < 0){
                    System.out.println(userId + " owes " + entry.getKey() + ": " + -entry.getValue());
                }
            }
        }
    }

    @Override
    public void showUserBalances(String userId) {
        if(!balanceMap.containsKey(userId)){
            System.out.println("No balances");
            return;
        }
        Map<String, Double> userBalance = balanceMap.get(userId);
        for(Map.Entry<String, Double> entry: userBalance.entrySet()){
            if(entry.getValue() > 0){
                System.out.println(entry.getKey() + " owes " + userId + ": " + entry.getValue());
            }
            else{
                System.out.println(userId + " owes " + entry.getKey() + ": " + -entry.getValue());
            }
        }
    }

}
