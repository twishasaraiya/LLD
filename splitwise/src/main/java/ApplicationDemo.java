import model.Balance;
import model.CommandType;
import model.ExpenseType;
import service.SplitwiseFacadeService;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentSplitStrategy;
import strategy.SplitStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDemo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        SplitwiseFacadeService splitwiseFacadeService = new SplitwiseFacadeService();

        // TODO: Use String Tokenizer
        String[] input = br.readLine().split(" ");
        while (true) {
            CommandType commandType = CommandType.valueOf(input[0]);
            switch (commandType) {
                case SHOW:
                    try {
                        Long userId = Long.valueOf(input[1]);
                        showBalance(splitwiseFacadeService.showBalance(userId));
                    } catch (Exception ex) {
                        showBalance(splitwiseFacadeService.showBalance());
                    }
                    break;
                case EXPENSE:
                    Long userId = Long.valueOf(input[1]);
                    Double amount = Double.valueOf(input[2]);
                    int splitBetweenUsersSize = Integer.parseInt(input[3]);
                    List<Long> splitBetweenUsers = new ArrayList<>();
                    for (int i = 1; i <= splitBetweenUsersSize; i++) {
                        splitBetweenUsers.add(Long.valueOf(input[3 + i]));
                    }
                    int lastIdx = 3 + splitBetweenUsersSize;
                    ExpenseType expenseType = ExpenseType.valueOf(input[lastIdx + 1]);
                    SplitStrategy splitStrategy = null;
                    switch (expenseType) {
                        case EXACT:
                            List<Double> amountList = new ArrayList<>();
                            for (int i = 1; i <= splitBetweenUsersSize; i++) {
                                amountList.add(Double.valueOf(input[lastIdx + 1 + i]));
                            }
                            Double sum = amountList.stream().reduce(0D, Double::sum);
                            // validate sum
                            Boolean isInputValid = sum.equals(amount);
                            System.out.println(amountList + " " + sum);
                            if (isInputValid) {
                                splitStrategy = new ExactSplitStrategy(splitBetweenUsers, amountList);
                            }else{
                                System.out.println("Sum of shares not equal to total sum");
                            }
                            break;
                        case EQUAL:
                            splitStrategy = new EqualSplitStrategy(amount, splitBetweenUsers);
                            break;
                        case PERCENT:
                            List<Double> percentList = new ArrayList<>();
                            for (int i = 1; i <= splitBetweenUsersSize; i++) {
                                percentList.add(Double.valueOf(input[lastIdx + 1 + i]));
                            }
                            Double sum1 = percentList.stream().reduce(0D, Double::sum);
                            System.out.println(percentList + " " + sum1);
                            // validate sum
                            Boolean isValidPercentDistribution = sum1.equals(100D);
                            if (isValidPercentDistribution) {
                                splitStrategy = new PercentSplitStrategy(amount, splitBetweenUsers, percentList);
                            }else {
                                System.out.println("Sum of shares not equal to total sum");
                            }
                            break;
                    }
                    if (splitStrategy != null) {
                        splitwiseFacadeService.setSplitStrategy(splitStrategy);
                        splitwiseFacadeService.addExpense(userId, amount, splitBetweenUsers, expenseType);
                    }
                    break;
                case SIMPLIFY:
                    /**
                     * User2 owes 1 : 620.0
                     * User3 owes 4 : 240.0
                     * User2 owes 4 : 240.0
                     * User1 owes 4 : 230.0
                     * User3 owes 1 : 1130.0
                     */
                    Boolean flagValue = Boolean.valueOf(input[1]);
                    splitwiseFacadeService.updateSimplifyBalanceFlag(flagValue);
                    break;
            }
            input = br.readLine().split(" ");
        }
    }

    private static void showBalance(List<Balance> balanceList){
        if(balanceList.isEmpty()){
            System.out.println("No balances");
        }else{
            for (Balance balance:
                 balanceList) {
                System.out.println("User" + balance.getUserId() + " owes " + balance.getOwesTo() + " : " + balance.getAmount());
            }
        }
    }
}
