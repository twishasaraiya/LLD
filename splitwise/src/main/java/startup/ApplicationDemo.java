package startup;


import enums.InputCommand;
import enums.SplitType;
import service.SplitWiseService;
import service.impl.SplitWiseServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
1. There should be a SplitwiseSevice which composed of UserManagement and ExpenseManagerService
2. From application demo input should be passed to SplitWiseService
3. Expense class should have Distribution type not, Distribution strategy
4. Expense Manager will have a Distribution strategy
5. In ExpenseManger key of map should be user_id instead of user object.
*/

public class ApplicationDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        SplitWiseService splitWise = new SplitWiseServiceImpl();

        splitWise.addUser("u1", "User1", null, null);
        splitWise.addUser("u2", "User2", null, null);
        splitWise.addUser("u3", "User3", null, null);
        splitWise.addUser("u4", "User4", null, null);

        StringTokenizer cmd;
        while(true){
            cmd = new StringTokenizer(br.readLine(), " ");
            InputCommand inputCommand = InputCommand.valueOf(cmd.nextToken());
            switch (inputCommand){
                case SHOW:
                    if(!cmd.hasMoreTokens()){
                        splitWise.showBalances();
                    }
                    else {
                        splitWise.showBalancesByUserId(cmd.nextToken());
                    }
                    break;
                case EXPENSE:
                    // EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
                    String expensePaidBy = cmd.nextToken();
                    double totalAmount = Double.parseDouble(cmd.nextToken());
                    int numParticipants = Integer.parseInt(cmd.nextToken());
                    List<String> participants = new ArrayList<>();
                    for(int i=0; i<numParticipants; i++){
                        participants.add(cmd.nextToken());
                    }
                    SplitType splitType = SplitType.valueOf(cmd.nextToken());
                    double[] values = null;
                    switch (splitType){
                        case EQUAL:
                            break;
                        case EXACT:
                        case PERCENT:
                            values = new double[numParticipants];
                            for(int i=0; i<numParticipants; i++){
                                values[i] = Double.parseDouble(cmd.nextToken());
                            }
                            break;
                    }
                    splitWise.addExpense(expensePaidBy, totalAmount, numParticipants, participants, splitType, values);
                    break;
                case EXIT:
                    System.exit(1);
            }
        }
    }
}
