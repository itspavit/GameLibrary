package GameLogic.Chess;

public class ChessGameManager
{
    // the interfaces for getting input from the players
    private PlayerInterface player_1;
    private PlayerInterface player_2;

    // the chess board
    private ChessBoard chessBoard;

    // the current state of the game
    private EGameState current_state;

    // is a piece selected
    private boolean piece_selected = false;

    // the coordinate of the selected piece
    private Coordinate selected_piece;

    // who's turn is it
    private boolean is_p1_turn = false;
    private boolean p1_use_white = false;

    GUIInterface gui;

    /**
     * initialize the game
     * @param p1 : the interface to player 1
     * @param p2 : the interface to player 2
     * @param _gui: the gui used to display the game
     * @param _is_p1_white : should player 1 use white pieces
     */
    public ChessGameManager(PlayerInterface p1, PlayerInterface p2, GUIInterface _gui, boolean _is_p1_white)
    {
        // initialize the chess board
        chessBoard = new ChessBoard(_is_p1_white);
        p1_use_white = _is_p1_white;

        // set the player interfaces
        player_1 = p1;
        player_2 = p2;

        player_1.SetGameManager(this);
        player_2.SetGameManager(this);

        // set the gui interface
        gui = _gui;

        // set game state to in play
        current_state = EGameState.IN_PLAY;

        // set the turn
        if(_is_p1_white)
        {
            is_p1_turn = true;
        }
        else
        {
            is_p1_turn = false;
        }
    }

    /**
     * handle position clicked input
     * @param player : the player that made the input command
     * @param click_pos : the position that was selected
     */
    public void HandlePositionClicked(PlayerInterface player, Coordinate click_pos)
    {
        // if the input is not from the player who's active turn it is then return
        if((!is_p1_turn && player == player_1) || (is_p1_turn && player == player_2))
        {
            return;
        }

        // if the selected piece was selected again then unselect that piece
        if((piece_selected) && (click_pos == selected_piece))
        {
            piece_selected = false;
            // unselect this piece in the GUI
            gui.SetSelectedPeice(click_pos, false);
        }

        // should the player be moving white pieces
        boolean use_white_pieces = ((is_p1_turn) && (p1_use_white)) || (!is_p1_turn && !p1_use_white);

        // the contents of the selected cell
        ESquareContents cell_content = chessBoard.AtCoordinate(click_pos);

        // if a piece is not already selected and the clicked position does not contain a piece then do nothing
        if(!piece_selected && (cell_content == ESquareContents.SQUARE_EMPTY))
        {
            return;
        }

        // if a piece is not already selected and the clicked position contains the wrong color piece then do nothing
        if(!piece_selected && (((cell_content == ESquareContents.WHITE_PIECE) && !use_white_pieces) || ((cell_content == ESquareContents.BLACK_PIECE) && use_white_pieces)))
        {
            return;
        }


         // if a piece has not been selected then
        if(!piece_selected && (((cell_content == ESquareContents.WHITE_PIECE) && use_white_pieces) || ((cell_content == ESquareContents.BLACK_PIECE) && !use_white_pieces)))
        {
            piece_selected = true;
            selected_piece = click_pos;
            gui.SetSelectedPeice(click_pos, true);
        }


        // if a piece is already selected then this must be an attempted move so attempt move
        if(piece_selected)
        {
            // attempt the move and store the result
            EMoveResult move_result = chessBoard.AttemptMove(selected_piece, click_pos, is_p1_turn);

            // let player choose new piece for pawn
            if(move_result == EMoveResult.PROMOTE_PAWN)
            {
                // update the selected piece to the position
                selected_piece = click_pos;
                gui.DisplayPromotPawn(is_p1_turn);
            }

            // if the move attempt was valid then update the game
            if(move_result == EMoveResult.VALID_MOVE)
            {
                // unselect the current piece
                piece_selected = false;
                gui.SetSelectedPeice(selected_piece, false);

                // set the player interface for the next turn
                if(is_p1_turn)
                {
                    player_1.SetToOthersTurn();
                    player_2.SetToYourTurn();
                }
                else
                {
                    player_1.SetToYourTurn();
                    player_2.SetToOthersTurn();
                }

                // set to the next players turn
                is_p1_turn = !is_p1_turn;

                // update the game
                gui.UpdateBoard(chessBoard.GetFormattedOutput());
            }
            else if(move_result == EMoveResult.OUT_OF_PIECE_RANGE)
            {
                gui.DisplayMessage("This piece cannot move here", is_p1_turn);
            }
            else if(move_result == EMoveResult.PLACE_ON_OWN_PIECE)
            {
                gui.DisplayMessage("You cannot capture your own piece", is_p1_turn);
            }


            // check the resulting game state
            EGameState game_state = chessBoard.GetGameStatus();

            if((game_state == EGameState.P1_WINS) || (game_state == EGameState.P2_WINS) || (game_state == EGameState.DRAW))
            {
                gui.DisplayGameFinished(game_state, chessBoard.GetP1Moves(), chessBoard.GetP2Moves(), chessBoard.GetP1Captures(), chessBoard.GetP2Captures());
            }
            else if(game_state == EGameState.P1_IN_CHECK)
            {
                gui.DisplayInCheck(true);
            }
            else if(game_state == EGameState.P2_IN_CHECK)
            {
                gui.DisplayInCheck(false);
            }
        }
    }

    /**
     * handles user input for promoting the pawn
     * @param type: the piece type to promote the pawn to
     */
    public void HandlePromotion(EPieceType type)
    {
        // unselect the current piece
        chessBoard.PromotePawn(selected_piece, type);
        piece_selected = false;
        gui.SetSelectedPeice(selected_piece, false);

        // set the player interface for the next turn
        if(is_p1_turn)
        {
            player_1.SetToOthersTurn();
            player_2.SetToYourTurn();
        }
        else
        {
            player_1.SetToYourTurn();
            player_2.SetToOthersTurn();
        }

        // set to the next players turn
        is_p1_turn = !is_p1_turn;

        // update the game
        gui.UpdateBoard(chessBoard.GetFormattedOutput());
    }

    /**
     * handle the game ended command
     */
    public void HandleGameEnded()
    {
        // check the resulting game state
        EGameState game_state = chessBoard.GetGameStatus();
        gui.DisplayGameFinished(game_state, chessBoard.GetP1Moves(), chessBoard.GetP2Moves(), chessBoard.GetP1Captures(), chessBoard.GetP2Captures());
    }
}
