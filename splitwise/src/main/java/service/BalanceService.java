package service;

import model.Balance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BalanceService {

    // paidBy -> owesTo
    // 4 -> 1: 480 - 100
    private Map<HashKey, Balance> balanceMap;
    private static Boolean SIMPLIFY_BALANCE = Boolean.FALSE;

    public BalanceService() {
        balanceMap = new HashMap<>();
    }

    public void setSimplifyBalanceFlag(Boolean balanceFlag){
        this.SIMPLIFY_BALANCE = balanceFlag;
    }

    public void upsertBalance(Long userId, Long owesTo, Double amount){
        if(balanceMap.containsKey(new HashKey(userId, owesTo))){
            updateBalance(userId, owesTo, amount);
        }else{
            createBalance(userId, owesTo, amount);
        }
        reBalance(userId, owesTo); // 1-> 4   , 4 -> 1

        if(SIMPLIFY_BALANCE.equals(Boolean.TRUE)){
//            simplifyBalance(userId, owesTo);
        }
    }

    private void updateBalance(Long userId, Long owesTo, Double amount){
        Balance balance = balanceMap.get(new HashKey(userId, owesTo));
        balance.setAmount(balance.getAmount() + amount);

    }

    private void reBalance(Long userId, Long owesTo){
        HashKey userOwnerKey = new HashKey(userId, owesTo);
        HashKey ownerUserKey = new HashKey(owesTo, userId);

        Balance userOwnerBalance = balanceMap.getOrDefault(userOwnerKey, null);
        Balance ownerUserBalance = balanceMap.getOrDefault(ownerUserKey, null);

        if(userOwnerBalance != null && ownerUserBalance != null){
            if(userOwnerBalance.getAmount() > ownerUserBalance.getAmount()){
                // user 1 owesTo 2 : 480
                // user 2 owesTo 1 : 250

                // update user 1 owesTo 2 : 480 - 250 - 230
                // remove user 2 owes to 1

                userOwnerBalance.setAmount(userOwnerBalance.getAmount() - ownerUserBalance.getAmount());
                balanceMap.remove(ownerUserKey);
            }else {
                // reverse of above
                // user 1 owesTo 2 : 250
                // user 2 owesTo 1 : 480

                // update user 2 owesTo 1 : 480 - 250 - 230
                // remove user 1 owes to 2
                ownerUserBalance.setAmount(ownerUserBalance.getAmount() - userOwnerBalance.getAmount());
                balanceMap.get(userOwnerKey);
            }
        }
    }
    private void createBalance(Long userId, Long owesTo, Double amount){
        Balance balance = new Balance(userId, owesTo, amount);
        balanceMap.put(new HashKey(userId, owesTo), balance);
    }

    public List<Balance> getBalanceByUserId(Long userId){
        List<Balance> balanceList = new ArrayList<>();
        for (Balance balance:
             balanceMap.values()) {
            if (balance.getUserId().equals(userId) || balance.getOwesTo().equals(userId)){
                balanceList.add(balance);
            }
        }
        return balanceList;
    }

    public List<Balance> getAllBalance(){
        return balanceMap.values().stream().collect(Collectors.toList());
    }

    private void simplifyBalance(Long userId, Long owesTo){
        System.out.println("Simplifying balance for user : " + userId + " who owesTo: " + owesTo);
        Balance userOwnsBalance = balanceMap.getOrDefault(new HashKey(userId, owesTo), null);

        if(userOwnsBalance != null){
            // find all the keys where owesTo user has some outstanding debts to other users
            LinkedList<HashKey> hashKeys = balanceMap.keySet().stream()
                    .filter(hashKey -> hashKey.getUserId().equals(owesTo))
                    .collect(Collectors.toCollection(LinkedList::new));
            System.out.println("User " + owesTo + " owesTo " + hashKeys.size() + " other users");
            while(userOwnsBalance.getAmount() > 0 && hashKeys.size() > 0){
                HashKey polledKey = hashKeys.poll();
                Balance balanceToSimplify = balanceMap.get(polledKey);
                System.out.println("User" + userId + " owes " + userOwnsBalance.getAmount() + " to user " + polledKey.getOwesToUserId());
                if(userOwnsBalance.getAmount() <= balanceToSimplify.getAmount()){
                    // since balance is not sufficient the record will exit but the amount will reduce

                    // since the user paid for the owner to something user.....he does not owe anything now to the user
                    break;
                }else{
                    // since balance is sufficient to clear the owesTo user's debt

                }
            }
        }
    }

    class HashKey{
        private final Long userId;
        private final Long owesToUserId;

        public HashKey(Long userId, Long owesToUserId) {
            this.userId = userId;
            this.owesToUserId = owesToUserId;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getOwesToUserId() {
            return owesToUserId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashKey hashKey = (HashKey) o;
            return Objects.equals(userId, hashKey.userId) && Objects.equals(owesToUserId, hashKey.owesToUserId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, owesToUserId);
        }
    }
}
