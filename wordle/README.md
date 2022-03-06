
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

## Sample Test Cases
// Add a few words in the dictionary
ADD_WORD_TO_DICTIONARY FLY 5
ADD_WORD_TO_DICTIONARY PLAY 5
ADD_WORD_TO_DICTIONARY APPLE 4
ADD_WORD_TO_DICTIONARY ROGUE 6
ADD_WORD_TO_DICTIONARY FLOOD 5
ADD_WORD_TO_DICTIONARY FLIPKART 7
// Register new players
REGISTER Hermione
REGISTER Ron
// Start a new game
START_NEW_WORDLE
// Random word is selected
// Hidden from player = Word -> APPLE
// Below information is revealed to the player
Number of letters = 5
Number of attempts = 4
// Play the game
GUESS Hermione BLOOD
Incorrect word
GUESS Hermione FLOOD
I P I I I
GUESS Hermione APPLE
I P I I I
C C C C C
GUESS Hermione ROGUE
Already played this round.
GUESS Ron FLOOD
I P I I I
GUESS Ron FLOOD
I P I I I
I P I I I
GUESS Ron FLOOD
I P I I I
I P I I I
I P I I I
GUESS Ron FLOOD
I P I I I
I P I I I
I P I I I
I P I I I
GUESS Ron FLOOD
Out of chances
SCORECARD CUR_ROUND
1 - Hermione - Score = 2
START_NEW_WORDLE
// New random word is selected
SCORECARD CUR_ROUND
—--
SCORECARD ALL_TIME
1 - Hermione - Score = 2