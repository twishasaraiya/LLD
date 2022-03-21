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
    private Map<HashKey, Double> balanceMap;
    private static Boolean SIMPLIFY_BALANCE = Boolean.FALSE;

    public BalanceService() {
        balanceMap = new HashMap<>();
    }

    public void setSimplifyBalanceFlag(Boolean balanceFlag){
        this.SIMPLIFY_BALANCE = balanceFlag;
    }

    public void upsertBalance(Long userId, Long owesTo, Double amount){

        HashKey userOwesTo = new HashKey(userId, owesTo);
        HashKey owesToUser = new HashKey(owesTo, userId);

        if(!balanceMap.containsKey(userOwesTo)){
            balanceMap.put(userOwesTo , amount);
            balanceMap.put(owesToUser, -1 * amount);
        }else{
            balanceMap.put(userOwesTo, balanceMap.get(userOwesTo) + amount);
            balanceMap.put(owesToUser, balanceMap.get(owesToUser) - amount);
        }

        if(SIMPLIFY_BALANCE.equals(Boolean.TRUE)){

        }
    }

    public List<Balance> getBalanceByUserId(Long userId){
        List<Balance> balanceList = new ArrayList<>();
        for (HashKey hashKey:
             balanceMap.keySet()) {
            if(hashKey.getUserId().equals(userId)){
                balanceList.add(new Balance(userId, hashKey.owesToUserId, balanceMap.get(hashKey)));
            }
        }
        return balanceList;
    }

    public List<Balance> getAllBalance(){
        List<Balance> balanceList = new ArrayList<>();
        for (Map.Entry<HashKey, Double> balance:
             balanceMap.entrySet()) {
            if(balance.getValue() > 0){
                balanceList.add(new Balance(balance.getKey().userId, balance.getKey().owesToUserId, balance.getValue()));
            }
        }
        return balanceList;
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
