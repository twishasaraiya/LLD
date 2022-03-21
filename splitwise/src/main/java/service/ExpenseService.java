package service;

import model.Expense;
import model.ExpenseType;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentSplitStrategy;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExpenseService {

    private final BalanceService balanceService;
    private SplitStrategy splitStrategy;

    public ExpenseService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    private Expense addExpense(Long initiatedBy, Double amount, List<Long> splitBetweenUsersList, ExpenseType expenseType, Optional<List<Double>> additionalDataList){
        return new Expense(expenseType, amount, initiatedBy, splitBetweenUsersList, additionalDataList);
    }

    private void setSplitStrategy(Expense expense) {
        SplitStrategy splitStrategy = null;
        switch (expense.getExpenseType()){
            case EQUAL:
                    splitStrategy = new EqualSplitStrategy(expense.getAmount(), expense.getSplitBetweenUsers());
                break;
            case PERCENT:
                splitStrategy = new PercentSplitStrategy(expense.getAmount(), expense.getSplitBetweenUsers(), expense.getAdditionalDataList().get());
                break;
            case EXACT:
                splitStrategy = new ExactSplitStrategy(expense.getAmount(), expense.getSplitBetweenUsers(), expense.getAdditionalDataList().get());

                break;
        }
        this.splitStrategy = splitStrategy;
    }

    public Expense handleExpense(Long initiatedBy, Double amount, List<Long> splitBetweenUsersList, ExpenseType expenseType, Optional<List<Double>> additionalDataList){
        Expense expense = addExpense(initiatedBy, amount, splitBetweenUsersList, expenseType, additionalDataList);

        setSplitStrategy(expense);

        // split the expense among user
        Map<Long, Double> userToAmountMap = splitStrategy.split();


        for (Long userId:
                expense.getSplitBetweenUsers()) {
            if(userId != initiatedBy){
                balanceService.upsertBalance(userId, initiatedBy, userToAmountMap.get(userId));
            }
        }

        return expense;
    }
}
