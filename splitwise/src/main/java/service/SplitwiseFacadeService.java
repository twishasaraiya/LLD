package service;

import model.Balance;
import model.Expense;
import model.ExpenseType;
import strategy.SplitStrategy;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SplitwiseFacadeService {

    private final BalanceService balanceService;
    private final ExpenseService expenseService; // record maintain

    public SplitwiseFacadeService() {
        this.balanceService = new BalanceService();
        this.expenseService = new ExpenseService(balanceService);
    }


    public List<Balance> showBalance(){
        return balanceService.getAllBalance();
    }

    public List<Balance> showBalance(Long userId){
        return balanceService.getBalanceByUserId(userId);
    }

    // TODO: Facade should not have any additional logic
    public void handleExpense(Long initiatedBy, Double amount, List<Long> splitBetweenUsersList, ExpenseType expenseType, Optional<List<Double>> additionalDataList){
        expenseService.handleExpense(initiatedBy, amount, splitBetweenUsersList, expenseType, additionalDataList);
    }

    public void updateSimplifyBalanceFlag(Boolean value){
        this.balanceService.setSimplifyBalanceFlag(value);
    }
}
