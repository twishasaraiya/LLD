package startup;


import enums.DistributionType;
import enums.InputCommand;
import model.Expense;
import model.User;
import service.ExpenseManager;
import service.ExpenseManagerImpl;
import strategy.EqualDistributionStrategy;
import strategy.ExactDistributionStrategy;
import strategy.IDistributionStrategy;
import strategy.PercentageDistributionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        User user1 = new User("u1", "User1");
        User user2 = new User("u2", "User2");
        User user3 = new User("u3", "User3");
        User user4 = new User("u4", "User4");


        Map<String, User> userManagement = new HashMap<>();
        userManagement.put(user1.getUserId(), user1);
        userManagement.put(user2.getUserId(), user2);
        userManagement.put(user3.getUserId(), user3);
        userManagement.put(user4.getUserId(), user4);


        ExpenseManager expenseManager = new ExpenseManagerImpl();
        StringTokenizer cmd;
        while(true){
            cmd = new StringTokenizer(br.readLine(), " ");
            InputCommand inputCommand = InputCommand.valueOf(cmd.nextToken());
            switch (inputCommand){
                case SHOW:
                    if(!cmd.hasMoreTokens()){
                        expenseManager.showAllUserBalances();
                    }
                    else {
                        expenseManager.showUserBalances(userManagement.get(cmd.nextToken()));
                    }
                    break;
                case EXPENSE:
                    // EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
                    User expensePaidBy = userManagement.get(cmd.nextToken());
                    double totalAmount = Double.parseDouble(cmd.nextToken());
                    int numParticipants = Integer.parseInt(cmd.nextToken());
                    List<User> participants = new ArrayList<>();
                    for(int i=0; i<numParticipants; i++){
                        participants.add(userManagement.get(cmd.nextToken()));
                    }
                    DistributionType distributionType = DistributionType.valueOf(cmd.nextToken());
                    IDistributionStrategy distributionStrategy = null;
                    double[] values;
                    switch (distributionType){
                        case EQUAL:
                            distributionStrategy = new EqualDistributionStrategy(totalAmount, numParticipants, new double[0]);
                            break;
                        case EXACT:
                             values = new double[numParticipants];
                            for(int i=0; i<numParticipants; i++){
                                values[i] = Double.parseDouble(cmd.nextToken());
                            }
                            distributionStrategy = new ExactDistributionStrategy(totalAmount, numParticipants, values);
                            break;
                        case PERCENT:
                            values = new double[numParticipants];
                            for(int i=0; i<numParticipants; i++){
                                values[i] = Double.parseDouble(cmd.nextToken());
                            }
                            distributionStrategy = new PercentageDistributionStrategy(totalAmount, numParticipants, values);
                            break;
                    }

                    Expense expense = new Expense();
                    expense.setPaidBy(expensePaidBy);
                    expense.setExpenseAmount(totalAmount);
                    expense.setParticipants(participants);
                    expense.setDistributionStrategy(distributionStrategy);
                    expenseManager.addExpense(expense);
                    break;
                case EXIT:
                    System.exit(1);
            }
        }
    }
}
