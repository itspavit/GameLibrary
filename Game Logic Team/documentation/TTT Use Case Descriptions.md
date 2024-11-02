# Use Case: Start Game
**Primary Actor:** Player 1 or Player 2  
**Goal:** initiate a new TicTacToe game

**Preconditions:**
* The players must be authenticated in the OMG system
* Both players must be available
* System must be ready to host a new game

**Trigger:** a player selects to start a new game

**Scenario:**
1. Player starts a new game
2. System verifies both players are ready
3. System randomly assigns X/O symbols to players
4. System initializes an empty board
5. System sets first players turn

**Exceptions:**
1. Issue with player authentication
2. Issues initializing game board
3. Can't find a second player after player 1 selects to start a new game
4. Network connection issues

**Postconditions:**
* New game starts with an empty board, and 2 players with assigned symbols.

**Priority:** High

**When Available:** When both players are authenticated OR when 2 different authenticated players select to start a game and neither are in an active game

**Frequency of use:** once per game session

**Channel to actor:** GUI interface

**Open Issues:**
* Should players choose their own symbol or be randomly assigned?
* How do 2 players get matched for a game?
* Should there be a time limit to initiate the game after a play selects to start a new game.

---

# Use Case: Make Move
**Primary Actor:** Current Player  
**Goal:** Place an X or O on the game board

**Preconditions:**
* A game is in process
* It's the player's turn
* Valid moves are available

**Trigger:** Either the previous player has just finished making a move, or the game has just begun and this player goes first.

**Scenario:**
1. Player selects an empty cell on the board
2. System checks if position is empty
3. System checks if it's that players turn
4. If all checks pass, system places player's symbol (X or O) in the selected position
5. System proceeds to check win and draw conditions

**Exceptions:**
1. Player selects an invalid position, system notifies player to select another position
2. Player tries to move when it's not their turn - system notifies the player to wait

**Postconditions:** The move is placed on the board

**Priority:** High

**When Available:** During a player's turn in an active game

**Frequency of use:** Multiple times per game session

**Channel to actor:** GUI Interface

**Open Issues:**
* Should there be a move time limit? If so, what happens after a timeout? The game could be deleted or forfeited by the player who timed out.

---

# Use Case: Forfeit
**Primary Actor:** Player  
**Goal:** To voluntarily end game before completion, the other player wins

**Preconditions:**
* There is an active game

**Trigger:** Player selects to forfeit the game

**Scenario:**
1. Player selects to forfeit the game
2. System requests confirmation
3. Player confirms forfeit
4. System rewards the win to the opponent and the game is moved to the update player data use case

**Exceptions:**
1. System fails to process forfeit
2. Player disconnects before forfeit is processed

**Postconditions:** Game ends with forfeit/win result

**Priority:** Low

**When Available:** During an active game

**Frequency of use:** Rarely, at most once per game session

**Channel to actor:** GUI interface

**Open Issues:**
* Should forfeits differ from wins in how player statistics are updated? If so, how?

---

# Use Case: Chat
**Primary Actor:** Player  
**Goal:** To communicate with the opponent during a game

**Preconditions:** Game is active and both players are connected

**Trigger:** Player initiates a chat message

**Scenario:**
1. Player opens chat
2. Player sends a message
3. System displays messages sent back and forth to both players

**Exceptions:**
1. Message fails to deliver
2. Opponent disconnected

**Postconditions:** Message delivered to opponent and both can view it

**Priority:** Low

**When Available:** During an active game

**Frequency of use:** Variable, but available during active game sessions

**Channel to actor:** GUI interface

**Secondary Actors:** Opponent

**Open Issues:**
* Should inappropriate messages be flagged?
* Should there be a character limit?
* Should the chat persist after the game ends?

---

# Use Case: Check Win
**Primary Actor:** N/A (triggered by Make Move)  
**Goal:** To determine if a winning condition has been met

**Preconditions:**
* A valid move has just been made
* A game is in progress

**Trigger:** A move has just been completed

**Scenario:**
1. System checks all rows for 3 matching symbols
2. System checks all columns for 3 matching symbols
3. System checks both diagonals for 3 matching symbols
4. If a win is found, the system moves to the end game use case
5. If no win is found, the system moves to the check draw use case

**Exceptions:**
1. System fails to check the win condition

**Postconditions:**
* Either the game is ended or the system checks for a draw

**Priority:** High

**When Available:** After a move is made

**Frequency of use:** Multiple times per game

**Channel to actor:** Internal system process

**Secondary Actors:** N/A

**Open Issues:** N/A

---

# Use Case: Check Draw
**Primary Actor:** N/A (triggered by Check Win)  
**Goal:** To determine if the game has reached a draw (cat's game)

**Preconditions:**
* No win has occurred
* A valid move has just been made

**Trigger:** No win after a move has been made

**Scenario:**
1. System checks if all of the board positions have been filled
2. If this is true, the system moves to the end game use case
3. If there are still empty positions, the system moves to the switch turn use case

**Exceptions:**
1. System fails at verifying board state

**Postconditions:**
* Either the game has ended as a draw or it's the next player's turn

**Priority:** High

**When Available:** After checking for a win condition after a move has been made

**Frequency of use:** Multiple times per game session

**Channel to actor:** Internal system process

**Secondary Actors:** N/A

**Open Issues:**
* Should a draw be determined before all the positions are full?

---

# Use Case: Switch Turn
**Primary Actor:** N/A (triggered by Check Draw)  
**Goal:** Change the active player after a valid move

**Preconditions:**
* A valid move has just been completed, and no win or draw was established

**Trigger:** Previous move completed without the game ending

**Scenario:**
1. The system identifies the current player
2. The system switches the current player to inactive, and the other player to be the active player
3. System updates the game state and notifies players of the turn change

**Exceptions:**
1. System fails to switch turns properly

**Postconditions:**
* Turn is switched to the other player

**Priority:** High

**When Available:** After a move with no win or draw

**Frequency of use:** Multiple times per game

**Channel to actor:** Internal system process

**Secondary Actors:** N/A

**Open Issues:** N/A

---

# Use Case: End Game
**Primary Actor:** N/A (triggered by Check Win, Check Draw, or Forfeit)  
**Goal:** Properly conclude a game session that has concluded without any errors

**Preconditions:** Win, draw, or forfeit occurs in an active game

**Trigger:** A game has either met the win or draw condition, or a player decides to forfeit

**Scenario:**
1. System verifies the active game has either met a win or draw condition, or that a player has selected to forfeit
2. The system stops accepting new moves
3. The system notifies both players that the game has ended
4. The game is deactivated

**Exceptions:**
1. System fails to deactivate the game

**Postconditions:**
* Game concluded and results given to each player (either one of them has won or it's a draw)

**Priority:** High

**When Available:** After a win or draw condition has been met, or a player forfeits

**Frequency of use:** Once per game session

**Channel to actor:** GUI interface (if forfeit) or internal system process (if win or draw)

**Open Issues:** N/A

---

# Use Case: System Timeout
**Primary Actor:** System  
**Goal:** To terminate inactive game sessions

**Preconditions:** There is an active game and the inactivity threshold has been reached

**Trigger:** Inactivity timer expires

**Scenario:**
1. System detects prolonged inactivity
2. System sends warning to players
3. If no response, system initiates game deletion

**Exceptions:**
1. System fails to detect timeout
2. Timer malfunction

**Postconditions:** Game moves to the delete game use case

**Priority:** Low

**When Available:** During an active game

**Frequency of use:** Rarely

**Channel to actor:** Internal system process

**Open Issues:**
* What should be the timeout duration?
* Should timeouts affect player statistics?

---

# Use Case: System Disconnect
**Primary Actor:** System  
**Goal:** Handle unexpected player disconnections

**Preconditions:**
* Game is active
* Connection monitoring is active

**Trigger:** Player connection is lost

**Scenario:**
1. System detects player disconnection
2. System attempts reconnection
3. If reconnection fails, initiate game deletion

**Exceptions:**
1. False disconnection detected
2. System inability to detect disconnection

**Postconditions:**
* Game will move on to the delete game use case

**Priority:** Medium

**When Available:** During an active game

**Frequency of use:** Rarely

**Channel to actor:** Internal system process

**Secondary Actors:** Players? Network?

**Open Issues:**
* What should the reconnection time window be?

---

# Use Case: Delete Game
**Primary Actor:** N/A (triggered by system timeout or disconnect)  
**Goal:** To remove games that have had some failure or errors arise from the player's record

**Preconditions:**
* A system error has occurred which has interfered with the validity of the game (at the fault of neither player)
* This could be a system timeout or disconnect

**Trigger:** System timeout or disconnect confirmed

**Scenario:**
1. System verifies deletion conditions
2. System deactivates game and notifies players that an error has occurred

**Exceptions:**
1. Deletion process fails

**Postconditions:**
* Game removed from system, Update player Data keeps the player's data as it was before the game started so it remains unaffected by the game failure

**Priority:** Medium

**When Available:** After system failure

**Frequency of use:** Rarely

**Channel to actor:** Internal System process

**Secondary Actors:** N/A

**Open Issues:**
* How long should game data be retained?
* Should there be an attempt to recover failed games?

---

# Use Case: Update Player Data
**Primary Actor:** N/A (triggered by End Game or Delete Game)  
**Goal:** To maintain accurate player statistics

**Preconditions:** Game has concluded or been deleted, player records are accessible

**Trigger:** Game has ended or been deleted

**Scenario:**
1. System identifies game outcome (win, loss, draw, deletion)
2. System retrieves player records
3. System updates relevant statistics according to game outcome
4. System saves updated records

**Exceptions:**
1. Database update failure

**Postconditions:** Player statistics updated in the database

**Priority:** High

**When Available:** Once an active game becomes deactivated/has been concluded

**Frequency of use:** Once per game

**Channel to actor:** Internal system process

**Secondary Actors:** Database

**Open Issues:**
* For failed games, should we just use this to ensure their player statistics are the same as they were before the game started?
* What are the relevant statistics that should be provided? Do player stats include time spent on each game, number of wins, losses, draws, etc?
