*this should be converted to a pdf before submission*

## Overview
### **In-Game Actions**
- move piece
- move opponents piece
- validate move
- jump piece
- promote piece
- show available moves

### **Meta Game Actions**
- start game
- rematch
- exit game
- game complete
- win
- lose
- forfeit
- opponent forfeits

### **Non-Game Actions**
*these could probably be done outside the game itself, so theyre available/work consistently/globally*
*but idk so ill leave them here for now*
- send chat message
- receive chat message
- disconnect from game

## In Game

### Use Case: Move Piece
Primary Actor: Player

Iteration: 1

Goal in Context: Move one of the player's pieces

Precondition: It is the player's turn

Trigger: The movePiece() function is called by from the GUI

Scenario:
  1. the player moves their piece in the GUI
  2. a movePiece() function is called
  3. the game checks if the move is allowed, or a piece has been jumped and updates the game state accordingly
  4. if the move is allowed, it is sent to the opponents game
 
Post Condition: the game checks if the move is allowed (see validate move)

Exceptions: N/A

Priority: Very High

When Available: Iteration 1

Frequency of Use: Very Often 

Secondary Actors: Opponent

Channel to Actor: The GUI interfacing the game

Open Issues: N/A


### Use Case: Move Opponent Piece
Primary Actor: Opponent

Iteration: 1

Goal in Context: Update the game with the opponents move

Precondition: it is the opponents turn

Trigger: the network code calls a moveOpponent() function

Scenario:
  1. the network code recieves a move from the opponent player's computer
  2. some moveOpponent() function is called
  3. the game checks if the move is allowed or a piece has been jumped, and updates the game state accordingly
  4. tell the GUI to redraw the new game state

Post Condition: the game checks if the move is allowed (see validate move)

Exceptions: invalid move

Priority: Very High

When Available: Iteration 1

Frequency of Use: Incredibly often, a lot of times per game

Channel to Actor: The Network Code/Internet

Secondary Actors: Player

Open Issues:
  - what happens if the received move is invalid (i.e. the opponent is cheating/modding)

### Use Case: Validate Move
Primary Actor: Player or Opponent

Frequency of Use: Incredibly often, a lot of times per game

Iteration: 1

Goal in Context: Make sure an attempted move follows the rules

Precondition: the player or opponent tries to move a piece

Trigger: 

Scenario:
  1. a player move a game piece, calling move()/moveOpponent()
  2. the game checks if the move is allowed
  3. the result of the check is returned to the movement code

Post Condition: 

Exceptions: N/A

Priority: Very High

When Available: Iteration 1 (may be updated with more complex rules later)

Frequency of Use: Incredibly often, lots of times per game

Channel to Actor: The GUI/network code

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Jump Piece
Primary Actor: Player or Opponent

Iteration: 1

Goal in Context: Jump one players piece over the other's, and remove the jumped piece from the board

Precondition: it is the correct player's turn

Trigger: the player/opponent makes a valid move that moves 2 spaces 

Scenario: 
  1. A player jumps the opponents piece through the GUI
  2. the movePiece() function verifies that they are allowed to jump the piece
  3. the players jumping piece is moved, and the opponents piece is removed from the board
  4. the GUI updates with the new game state
Post Condition: the jumped piece gets removed from the board, and the GUI shows the new game state

Exceptions: N/A

Priority: Very High

When Available: Iteration 1, After movement validation

Frequency of Use: Very often, many times a game

Channel to Actor: The GUI/Network code

Secondary Actors: N/A

Open Issues: N/A
 

### Use Case: Promote Piece
Primary Actor: Player or Opponent

Iteration: 1

Goal in Context: Promote a piece to a king

Precondition: A piece is being moved

Trigger: The piece reached the other side of the board

Scenario:
  1. A player moves their piece into the last row of the board
  2. the piece gets promoted to a king, and can now move both directions
  3. the game state and GUI update with the new changes
Post Condition: the piece becomes a king, and the GUI shows the new game state

Exceptions: N/A

Priority: High 

When Available: Iteration 1, once the other basic rules have been implemented

Frequency of Use: A few times per game

Channel to Actor: GUI/Network code

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Show Available Moves
Primary Actor: Player 

Iteration: 2

Goal in Context: Show where a piece cam move on the board

Precondition: It is the players turn

Trigger: The player selects a game piece

Scenario:
  1. the player chooses what piece they wan to move
  2. all legal moves for that piece are highlighted on the board
  3. if the player selects a legal move the pice gets moved, otherwise it deselects the piece

Post Condition: The player can select where they want to move the piece

Exceptions: N/A

Priority: mid-low

When Available: once all the game rules and systems are already working

Frequency of Use: a few times per game turn

Channel to Actor: The GUI 

Secondary Actors: N/A

Open Issues: N/A


## Meta Game 

### Use Case: Start Game
Primary Actor: Player and Opponent

Iteration: 1

Goal in Context: start a new game of checkers

Precondition: both players have opened the game, and start a game with the matchmaking system

Trigger: the matchmaking system creates a match with the player and opponent

Scenario:
  1. the player and opponent with similar skill levels both look for a new match in the matchmaking system
  2. the matchmaking system see's that have a similar skill ranking, and pairs them together
  3. the matchmaking system creates a new game between the player and opponent

Post Condition: The player and opponent can play checkers against each other

Exceptions: N/A

Priority: High

When Available: Iteration 1, will likely be the 1st thing implemented

Frequency of Use: once per game

Channel to Actor: GUI/Network code

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Rematch
Primary Actor: Player and Opponent

Iteration: 2

Goal in Context: Let the player and opponent start a new game once someone has won

Precondition: The game has ended, and the results screen is shown (see Game Complete)
 
Trigger: Both players indicate they want a rematch

Scenario:
  1. The game of checkers ends, and the results screen is shown
  2. Both the opponent and player click the rematch button
  3. A new game of checkers is started

Post Condition: The game restarts (see start game)

Exceptions: 
  - A player selects rematch, but then disconnects, making rematching impossible

Priority: Medium

When Available: Iteration 2, after game logic and systems are done

Frequency of Use: zero to many times per session

Channel to Actor: GUI/Network code

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Exit Game
Primary Actor: Player

Iteration: 2

Goal in Context: Let the player leave the once the game is over

Precondition: The game is finished and the results screen is being shown (see Game Complete)

Trigger: The player selects the exit game button

Scenario:
  1. The game of checkers ends, and the results screen is shown
  2. The player clicks the exit game button
  3. The opponents stops being allowed to rematch
  3. The game is closed

Post Condition: The game closes

Exceptions: N/A

Priority: Medium - High

When Available: Once results screen is implemented

Frequency of Use: Once per session

Channel to Actor: GUI

Secondary Actors: Opponent

Open Issues: N/A

### Use Case: Game Complete
Primary Actor: Player

Iteration: 1

Goal in Context: show the player the results of the game, and give the option to rematch or exit

Precondition: the player finishes making their move, and it is the opponents turn

Trigger: the opponent has no pieces they can move on their turn

Scenario:
  1. the player jumps the opponent's last piece, or blocks all their moves
  2. the opponent has no pieces they can move on their turn
  4. the players win rating gets updated
  5. the game complete screen is shown, letting them know they won

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:
### Use Case: Win
Primary Actor: Player

Iteration: 1

Goal in Context: let the player know they won the game, and update their rating/statistics

Precondition: the player finishes making their move, and it is the opponents turn

Trigger: the opponent has no pieces they can move on their turn

Scenario:
  1. the player jumps the opponent's last piece, or blocks all their moves
  2. the opponent has no pieces they can move on their turn
  4. the players win rating gets updated
  5. the game complete screen is shown, letting them know they won

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Lose
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Forfeit
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Opponent Forfeit
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Send Chat Message
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Receive Chat Message
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

Open Issues:


### Use Case: Disconnect From Game
Primary Actor:

Iteration:

Goal in Context:

Precondition:

Trigger:

Scenario:

Post Condition:

Exceptions:

Priority:

When Available:

Frequency of Use:

Channel to Actor:

Secondary Actors:

#Open Issues:
