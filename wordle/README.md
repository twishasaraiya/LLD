
## Problem
https://leetcode.com/discuss/interview-question/1763279/Flipkart-or-SDE-1-or-Machine-Coding-Round

## Requirement
1. User can register with name
2. User can start new game
3. Single user can play only one round....should not be allowed to play same round again
4. System pre-populated with a list of words and difficulty before start of the game
5. User get Y turns == difficulty to guess the word per round
6. Player is shown hints after each guess
7. System should maintain curr score and overall score
8. System should display scores of all the non-zero players in descending round

## Commands
1. REGISTER player
2. ADD_WORD_TO_DICTIONARY word difficulty
3. START_NEW_WORDLE
4. GUESS player word
5. SCORECARD CUR_ROUND / ALL_TIME

## Entities

1. Player
2. Wordle
3. ScoreBoard
4. Round