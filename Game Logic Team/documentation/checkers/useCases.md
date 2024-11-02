*this should be converted to a pdf before submission*

## **In-Game Actions**
- move piece
- move opponents piece
- validate move
- jump piece
- promote piece
- show available moves

## **Meta Game Actions**
- choose starting player
- rematch
- win
- lose
- forfeit
- opponent forfeits

## **Non-Game Actions**
*these could probably be done outside the game itself, so theyre available/work consistently/globally*
*but idk so ill leave them here for now*
- send chat message
- receive chat message
- disconnect from game


## Use Case: Move Piece
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


## Use Case: Move Opponent Piece
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

## Use Case: Validate Move
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


## Use Case: Jump Piece
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
 

## Use Case: Promote Piece
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

When Available: iteration 1, once the other basic rules have been implemented

Frequency of Use: A few times per game

Channel to Actor: GUI/Network code

Secondary Actors: N/A

Open Issues: N/A


## Use Case: Show Available Moves
Primary Actor: Player 

Iteration: 2

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



## Use Case: Choose Starting Player
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


## Use Case: Rematch
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


## Use Case: Win
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


## Use Case: Lose
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


## Use Case: Forfeit
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


## Use Case: Opponent Forfeit
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


## Use Case: Send Chat Message
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


## Use Case: Receive Chat Message
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


## Use Case: Disconnect From Game
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
