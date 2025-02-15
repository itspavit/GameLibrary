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
- send chat message
- receive chat message

## In Game

### Use Case: Move Piece
Primary Actor: Player

Iteration: 1

Goal in Context: Move one of the player's pieces

Precondition: It is the player's turn

Trigger: The player moves a piece in the GUI

Scenario:
  1. The player moves their piece in the GUI
  2. The game checks if the move is allowed, or a piece has been jumped and updates the game state accordingly
  3. If the move is allowed, it is sent to the opponents game
 
Post Condition: The game checks if the move is allowed (see validate move)

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

Trigger: the network code receives a move from the opponent

Scenario:
  1. The network code receives a move from the opponent player's computer
  2. The game checks if the move is allowed or a piece has been jumped, and updates the game state accordingly
  3. Tell the GUI to redraw the new game state

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

Trigger: the movement code checks if the move is valid

Scenario:
  1. A player moves a game piece
  2. The game checks if the move is allowed
  3. The result of the check is returned to the piece movement code

Post Condition: the movement code allows/prevents the move from happening

Exceptions: N/A

Priority: Very High

When Available: Iteration 1 (more complex rules may be updated in later)

Frequency of Use: Incredibly often, lots of times per game

Channel to Actor: The GUI/network code

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Jump Piece
Primary Actor: Player or Opponent

Iteration: 1

Goal in Context: Jump one players piece over the other's, and remove the jumped piece from the board

Precondition: It is the correct player's turn

Trigger: The player/opponent makes a valid move that moves 2 spaces 

Scenario: 
  1. A player jumps the opponents piece through the GUI
  2. The movement code verifies that they are allowed to jump the piece
  3. The players jumping piece is moved, and the opponents piece is removed from the board
  4. The GUI updates with the new game state

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
  2. The piece gets promoted to a king, and can now move both directions
  3. The game state and GUI update with the new changes
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

Goal in Context: Show the player where a piece cam move on the board

Precondition: It is the players turn

Trigger: The player selects a game piece

Scenario:
  1. the player chooses what piece they wan to move
  2. all legal moves for that piece are highlighted on the board
  3. if the player selects a legal move the pice gets moved, otherwise it deselects the piece

Post Condition: The player can select where they want to move the piece

Exceptions: N/A

Priority: Medium - Low

When Available: once all the game rules and systems are already working

Frequency of Use: a few times per game turn

Channel to Actor: The GUI 

Secondary Actors: N/A

Open Issues: N/A


## Meta Game 

### Use Case: Start Game
Primary Actor: Player and Opponent

Iteration: 1

Goal in Context: Start a new game of checkers

Precondition: Both players have opened the game, and start a game with the matchmaking system, or rematch button

Trigger: The matchmaking system creates a match with the player and opponent

Scenario:
  1. The player and opponent with similar skill levels both look for a new match in the matchmaking system
  2. The matchmaking system see's that have a similar skill ranking, and pairs them together
  3. The matchmaking system creates a new game between the player and opponent

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

Frequency of Use: Zero to many times per session

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
  4. The game is closed

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

Goal in Context: Show the player the results of the game, and give the option to rematch or exit

Precondition: The player and opponent are in the middle of a game

Trigger: The game has ended from either player winning or forfeiting

Scenario:
  1. A player wins the game, and signals to show the ending screen
  2. The end screen is shown, with the results of the game
  3. The player is able to rematch or quit the game

Post Condition: The player see their results and can rematch or quit the game

Exceptions: N/A

Priority: High 

When Available: Iteration 1, after game rules are implemented

Frequency of Use: Once per game

Channel to Actor: GUI

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Win
Primary Actor: Player

Iteration: 1

Goal in Context: Let the player know they won the game, and update their rating/statistics

Precondition: The player finished their move, and it is the opponents turn

Trigger: The opponent has no pieces they can move on their turn

Scenario:
  1. The player jumps the opponent's last piece, or blocks all their moves
  2. The opponent has no pieces they can move on their turn
  3. The players win rating gets updated
  4. The game complete screen is shown, letting them know they won

Post Condition: The results screen is shown (see game complete)

Exceptions: N/A

Priority: High

When Available: Iteration 1, with other game rules implementation

Frequency of Use: Up to once a game

Channel to Actor: GUI

Secondary Actors: N/A

Open Issues: N/A


### Use Case: Lose
Primary Actor: Opponent

Iteration: 1

Goal in Context: Let the player know they lost the game, and update their rating/statistics

Precondition: The opponent has finished their move, and it is the player's turn

Trigger: The player has no pieces they can move on their turn

Scenario:
  1. The opponent jumps the player's last piece, or blocks all their moves
  2. The player has no pieces they can move on their turn
  3. The players win rating gets updated
  4. The game results screen is shown, letting them know they won

Post Condition: The results screen is shown (see game complete)

Exceptions: N/A

Priority: High

When Available: Iteration 1, with other game rules implementation

Frequency of Use: Up to once a game

Channel to Actor: GUI

Secondary Actors: Player

Open Issues: N/A


### Use Case: Forfeit
Primary Actor: Player

Iteration: 2

Goal in Context: Let the player end the game before it reaches a win/lose state

Precondition: The player is playing a game with the opponent

Trigger: The player clicks a forfeit button in the GUI

Scenario:
  1. The player wants to end the game, and clicks the forfeit button
  2. The game is registered as a loss in the player's rating
  3. The resignation is sent to the opponent, letting them know they've won
  4. The game ends, and the player is sent to the result screen

Post Condition: The results screen is shown (see game complete)

Exceptions: N/A

Priority: Medium

When Available: Iteration 2, after game rules have been completed

Frequency of Use: Low, up to once a game

Channel to Actors: GUI/Network code

Secondary Actors: Opponent

Open Issues: N/A


### Use Case: Opponent Forfeit
Primary Actor: Opponent

Iteration: 2

Goal in Context: Tell the player that their opponent has forfeited

Precondition: The player is playing a game with the opponent

Trigger: The network code receives data saying that the opponent has forfeited

Scenario:
  1. The network code receives data saying the opponent has forfeited
  2. The game is registered as a win in the player's rating
  3. The game ends, and the player is sent to the result screen

Post Condition: The results screen is shown (see game complete)

Exceptions: N/A

Priority: Medium

When Available: Iteration 2, after game rules have been completed

Frequency of Use: Low, up to once a game

Channel to Actors: GUI/Network code

Secondary Actors: Player

Open Issues: N/A


### Use Case: Send Chat Message
Primary Actor: Player

Iteration: 1

Goal in Context: Let the player send a chat message to their opponent

Precondition: The player has been matched with an opponent 

Trigger: The player sends a message through the GUI

Scenario:
  1. The player types their message in the chat and presses enter
  2. The message is sent though the network code and received by the opponent
  3. The opponent sees the message in their game

Post Condition: The opponent sees the players message

Exceptions: 
  - Network errors

Priority: High (in project outline document)

When Available: Iteration 1, after game logic

Frequency of Use: Infrequently to many times per game 

Channel to Actor: GUI/Network code

Secondary Actors: Opponent

Open Issues:
  - Should there be any moderation/filtering/reporting system in place


### Use Case: Receive Chat Message
Primary Actor: Opponent

Iteration: 1

Goal in Context: Let the player receive chat messages from their opponent

Precondition: The player has been matched with an opponent 

Trigger: The network code receives a message from the opponent

Scenario:
  1. The network code receives a message sent from the opponent
  2. The game receives the message, and displays it in the GUI for the player

Post Condition: The player sees the opponent's message

Exceptions: 
  - Network errors

Priority: High (in project outline document)

When Available: Iteration 1, after game logic

Frequency of Use: Infrequently to many times per game 

Channel to Actor: GUI/Network code

Secondary Actors: Player

Open Issues:
  - Should you be able to disable/block/filter chat messages

