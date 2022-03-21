import model.Balance;
import model.CommandType;
import model.ExpenseType;
import service.SplitwiseFacadeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class ApplicationDemo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        SplitwiseFacadeService splitwiseFacadeService = new SplitwiseFacadeService();

        // TODO: Use String Tokenizer
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        List<Double> additionalDataList = null;
        while (stringTokenizer.hasMoreTokens()) {
            CommandType commandType = CommandType.valueOf(stringTokenizer.nextToken());
            switch (commandType) {
                case SHOW:
                    try {
                        Long userId = Long.valueOf(stringTokenizer.nextToken());
                        showBalance(splitwiseFacadeService.showBalance(userId));
                    } catch (Exception ex) {
                        showBalance(splitwiseFacadeService.showBalance());
                    }
                    break;
                case EXPENSE:
                    Long userId = Long.valueOf(stringTokenizer.nextToken());
                    Double amount = Double.valueOf(stringTokenizer.nextToken());
                    int splitBetweenUsersSize = Integer.parseInt(stringTokenizer.nextToken());
                    List<Long> splitBetweenUsers = new ArrayList<>();
                    for (int i = 1; i <= splitBetweenUsersSize; i++) {
                        splitBetweenUsers.add(Long.valueOf(stringTokenizer.nextToken()));
                    }
                    int lastIdx = 3 + splitBetweenUsersSize;
                    ExpenseType expenseType = ExpenseType.valueOf(stringTokenizer.nextToken());
                    switch (expenseType) {
                        case EXACT:
                        case PERCENT:
                            additionalDataList = new ArrayList<>();
                            for (int i = 1; i <= splitBetweenUsersSize; i++) {
                                additionalDataList.add(Double.valueOf(stringTokenizer.nextToken()));
                            }
                            break;
                        case EQUAL:
                            break;
                    }
                    try {
                        splitwiseFacadeService.handleExpense(userId, amount, splitBetweenUsers, expenseType, Optional.ofNullable(additionalDataList));

                    }catch (Exception e){
                        System.out.println("Exception : " + e.getMessage());
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
                    Boolean flagValue = Boolean.valueOf(stringTokenizer.nextToken());
                    splitwiseFacadeService.updateSimplifyBalanceFlag(flagValue);
                    break;
            }
            stringTokenizer = new StringTokenizer(br.readLine(), " ");
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
