## Snake and Ladder

### Problem Link
https://workat.tech/machine-coding/practice/snake-and-ladder-problem-zgtac9lxwntg 

### Solution Link
https://workat.tech/machine-coding/editorial/how-to-design-snake-and-ladder-machine-coding-ehskk9c40x2w 

### Requirements
- Generate random number between 1-6 for each roll.
- Each player rolls the dice for his/her turn.
- If player reaches boardSize then he/she wins the game. Board cells are numbered from 1-boardSize. 0 represent start position.
- If dice value exceeds the player's position beyond board size, then skip the move.
- There could be another snake/ladder at the tail of the snake or the end position of the ladder and the piece should go up/down accordingly.

### Optional Requirements
- [X] In case of more than 2 players, the game continues until only one player is left. 
- [X] 2 dice instead of 1.
- [X] board size customisable
- [X] On getting a 6, you get another turn and on getting 3 consecutive 6s, all the three of those get cancelled.

### Commands

### Entities
- SnakeAndLadderBoard -->  Map<head_pos, tail_pos> snakes, Map<start_pos, end_pos> ladder,  List<Player> players {players who are playing the game}, boardSize, addSnakePosition(), addLadderPosition(), addPlayer(), playGame()
- Player --> playerName, currentPosition, isWon
- Dice --> rollDice()