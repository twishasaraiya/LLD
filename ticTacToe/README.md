## Tic Tac Toe

### Problem Link
https://workat.tech/machine-coding/practice/design-tic-tac-toe-smyfi9x064ry 

### Requirements
- Row is numbered from 1-3 and column is numbered from 1-3 (1-based indexing).
- Check the validity of move after each turn.
- For invalid move, same player plays again.
- Print board after every valid move.

### Optional Requirements
- [X] Grid size customisable 
- [X] Different types of pieces other than 'X' and '0'
- [X] More than 2 players or piece types

### Commands

### Entities
- Cell --> row, col, piece
- Board --> gridSize, grid = Cell[][], initializeBoard(), printBoard()
- Player --> playerName, piece
- GameService --> gridSize, Board, count, Queue<Player> players, addPlayer(), isValidMove(), hasWonGame(), playGame(), hasNextTurn()