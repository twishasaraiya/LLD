## Chess Validator

### Problem Link
https://workat.tech/machine-coding/practice/design-chess-validator-to77d8oqpx2h

### Requirements
- 2 players play the game.
- Player with white pieces play first.
- A player may move only to an empty cell unless it is trying to capture a piece of the other player.
- A piece is captured when a player moves to a position currently occupied by a piece of the opponent.
- For invalid move, same player plays again.
- The user will make a move by entering the start position and the end position.

### Optional Requirements
- [ ] Grid size customisable.
- [ ] Start position customisable.
- [ ] Allow new types of pieces with any type of move.
- [ ] Change the move style of any existing pieces.


### Entities
- Model
  - Player --> id, playerName, color
  - Cell --> row, col, Piece
  - Piece --> pieceType, colorType, isValidColorMove(), isValidMove()
  - Board --> Cell[8][8], size
  
- Enum
  - PieceType
  - ColorType
  
- Service
  - ChessService --> Player[], board, play()