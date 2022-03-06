package controller;

import model.Player;
import model.ScorecardType;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ScoreboardService {

    private PriorityQueue<Player> currentRoundRanking;
    private PriorityQueue<Player> allTimeRanking;

    private static final Comparator<Player> currScoreComparator = (Player a, Player b) -> b.getCurrScore() - a.getCurrScore();
    private static final Comparator<Player> allTimeScoreComparator = (Player a, Player b) -> b.getAllTimeScore() - a.getAllTimeScore();

    public ScoreboardService() {
        currentRoundRanking = new PriorityQueue<>(currScoreComparator);
        allTimeRanking = new PriorityQueue<>(allTimeScoreComparator);
    }

    public void updatePlayerCurrentRanking(Player player){
        currentRoundRanking.add(player);
    }

    public void updatePlayerAllTimeRanking(Player player){
        allTimeRanking.add(player);
    }

    public void removeOldCurrentScore(Player player){
        currentRoundRanking.remove(player);
    }


    public void removeOldAllTimeScore(Player player){
        allTimeRanking.remove(player);
    }

    private void displayRanking(PriorityQueue<Player> rankingList, ScorecardType scorecardType){
        int position = 1;
        if(rankingList.size() == 0)
            System.out.println("—--");
        for (Player player : rankingList) {
            System.out.println(position + " - " + player.getName() + " - Score = " + getScoreByBoardType(player, scorecardType));
            position++;
        }
    }

    private Integer getScoreByBoardType(Player player, ScorecardType scorecardType){
        switch (scorecardType){
            case CUR_ROUND:
                return player.getCurrScore();
            case ALL_TIME:
                return player.getAllTimeScore();
        }
        return null;
    }

    public void displayScoreCard(String type){
        ScorecardType scorecardType = ScorecardType.valueOf(type);
        switch (scorecardType){
            case CUR_ROUND:
                displayRanking(currentRoundRanking, scorecardType);
                break;
            case ALL_TIME:
                displayRanking(allTimeRanking, scorecardType);
                break;
        }
    }

    public void resetCurrentRanking(){
        currentRoundRanking = new PriorityQueue<>(currScoreComparator);
    }
}