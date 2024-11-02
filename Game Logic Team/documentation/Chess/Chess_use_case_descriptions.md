## Use Case Descriptions For Chess Game Logic:

### Use case: Start Game
**Iteration:** 1.0

**Primary actor:** Player

**Goal in context:** Allow player to start a game of chess

**Preconditions:**
* The Game app has been opened
* The app is on the game menu

**Trigger:** The user selects the start game button

**Scenario:**
1. The game enters play mode

**Exceptions:** 

**Postconditions:**
1. The game is now in play mode and the user can begin playing the game

**Channel to actor:** The user input via GUI or command line

**Open Issues:**
* How is the opponent chosen, will each game implement a way of handling the
opponent selection or will this be implemented by some other system done before the start game is selected
* How do we determine who plays first?

### Use case: Quit Game
**Iteration:** 1

**Primary actor:** Player

**Goal in context:** Quit the game

**Preconditions:**
* the game is currently in the play mode
* the game is not finished (neither player has won)

**Trigger:** The player presses the Quit Game button

**Scenario:**
* The game stops
* The app returns to the game menu 

**Exceptions:**

**Postconditions:**
* The app in game menu mode

**Channel to actor:**
* user input via GUI

**Open Issues:**
* If a player hits the quit game button should it show a summary of the game so far before it returns to the main menu
, should it display one of those annoying "are you sure you want to quit" popups. 


### Use case: Play Turn
**Iteration:** 1

**Primary actor:** Player

**Goal in context:** Allow the user to make a valid chess move

**Preconditions:** 
* the game is in play mode
* It is this players turn

**Trigger:** The other player has finished their turn

**Scenario:** 
1. The user selects a chess piece to move by clicking on it in the GUI
2. The user selects the position to move the piece to
   1. if the position is not valid, let the user try selecting the piece and position again
   2. if the position is valid, then move the piece
3. Check if the move is a finishing move
    1. if the game is won, enter the game won state
   2. if the game is a draw, enter the draw state
   3. if the game is not over, begin the other players turn

**Exceptions:**

**Postconditions:**
* either the game is won or the other player has begun their turn
**Channel to actor:** GUI

**Open Issues:**


### Use case: Game Won (Checkmate)
**Iteration:** 1

**Primary actor:** Player

**Goal in context:** Display game stats and show who won

**Preconditions:**
* The game is in the play mode
* The last move resulted in a checkmate

**Trigger:** Last move resulted in checkmate

**Scenario:**
1. The game should display stats to the players via the GUI
2. The game should make visible the Exit button

**Exceptions:**

**Postconditions:**
* the Exit button is visible to the player

**Channel to actor:** GUI

**Open Issues:**
* how will the stats be displayed when the game ends, If it is through an overlay popup or by going to a different GUI scene then there would need to be an exit button,
If not then the quit game button can function as the exit button

### Use case: Exit Game on Finish
**Iteration:** 1

**Primary actor:** Player

**Goal in context:** Exit the game

**Preconditions:**
* the game is currently in the game won mode

**Trigger:** The player presses the Exit Game button

**Scenario:**
* The app returns to the game menu

**Exceptions:**

**Postconditions:**
* The app in game menu mode

**Channel to actor:**
* user input via GUI

**Open Issues:**

### Use case: Dead(Draw) Position
**Iteration:** 1

**Primary actor:** Player

**Goal in context:** Display game stats and say it is a draw

**Preconditions:**
* The game is in the play mode
* The last move resulted in a draw

**Trigger:** Last move resulted in a dead position (draw)

**Scenario:**
1. The game should display stats to the players via the GUI
2. The game should make visible the Exit button

**Exceptions:**

**Postconditions:**
* the Exit button is visible to the player

**Channel to actor:** GUI

**Open Issues:**
* how will the stats be displayed when the game ends, If it is through an overlay popup or by going to a different GUI scene then there would need to be an exit button,
  If not then the quit game button can function as the exit button

## Use Case: Capture
**Iteration:** 1  
**Primary Actor:** Player  
**Goal in context:** Show that a chess piece has been captured
**Preconditions**:  
* The game is in play mode
* The player is able to move a piece into a square occupied by an enemy piece during their turn  

**Trigger:** The player moves one of their pieces into a square occupied by an enemy piece  
**Scenario:**  
1. The piece occupying the space should be replaced by the piece being moved
2. The piece removed should be tallied
3. Game should check the game state

**Exceptions:**
**Post Conditions:**
* The piece that previously existed in the space has been removed from the board and been replaced by the moved piece

**Channel to actor:** Graphical Representation of the game board
**Open Issues:** N/A

## Use Case: Promotion
**Iteration:** 1  
**Primary Actor:** Player  
**Goal in context:** Promote a pawn to a piece of the players choice
**Preconditions**:
* The game is in play mode
* The player is able to move a pawn into the last row of the board

**Trigger:** The player moves one of their pawns into a square on the end row of the board
**Scenario:**  
1. The pawn should be removed from the board
2. The player should be prompted to choose a new type of piece
3. The new piece should be located in the same square the pawn was removed from
4. The game state should be checked

**Exceptions:**
**Post Conditions:**
* The pawn that previously existed in the space has been removed from the board and been replaced by the chosen piece

**Channel to actor:** Graphical Representation of the game board
**Open Issues:** N/A


## Use Case: Check
**Iteration:** 1  
**Primary Actor:** Player  
**Goal in context:** Show that a player is in check
**Preconditions**:
* The game is in play mode
* The player is able to move a piece into a square occupied by an enemy king.

**Trigger:** The player moves one of their pieces into a square where it can now move into a square occupied by the enemy king   
**Scenario:**
1. The players should notified that a player is in check and indicate the checked king

**Exceptions:**  
**Post Conditions:**
* The player who is now in check should only be able to move their king out of check or move a piece into the path of the checking piece

**Channel to actor:** Graphical Representation of the game board
**Open Issues:**