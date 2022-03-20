package service;

import model.Balance;
import model.Expense;
import model.ExpenseType;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;

public class SplitwiseFacadeService {

    private final BalanceService balanceService;
    private final ExpenseService expenseService; // record maintain
    private SplitStrategy splitStrategy;    // TODO:

    public SplitwiseFacadeService() {
        this.balanceService = new BalanceService();
        this.expenseService = new ExpenseService();
    }


    public List<Balance> showBalance(){
        return balanceService.getAllBalance();
    }

    public List<Balance> showBalance(Long userId){
        return balanceService.getBalanceByUserId(userId);
    }

    // TODO: Facade should not have any additional logic
    public void addExpense(Long initiatedBy, Double amount, List<Long> splitBetweenUsersList, ExpenseType expenseType){
        // store new expense
        Expense expense = expenseService.addExpense(initiatedBy, amount, splitBetweenUsersList, expenseType);

        // split the expense among user
        Map<Long, Double> userToAmountMap = splitStrategy.split();

        // handle balance for all the users
        for (Long userId:
             expense.getSplitBetweenUsers()) {
            if(userId != initiatedBy){
                balanceService.upsertBalance(userId, initiatedBy, userToAmountMap.get(userId));
            }
        }
    }

    public void setSplitStrategy(SplitStrategy splitStrategy){
        this.splitStrategy = splitStrategy;
    }

    public void updateSimplifyBalanceFlag(Boolean value){
        this.balanceService.setSimplifyBalanceFlag(value);
    }
}
