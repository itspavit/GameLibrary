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


### Use case: Game Won
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

