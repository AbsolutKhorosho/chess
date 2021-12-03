package view.gui;

import model.Board;
import model.Board.State;
import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;
import model.pieces.Piece;

/**
 * A simple controller class that
 * processes the input that the
 * GUI sends to the model.
 *
 * @author Matt Stetter
 */
public class ChessFeaturesController implements FeaturesController {
  private Board board;
  private BoardGUIView view;
  private boolean gameRunning;
  private boolean firstMoveMade;
  private PiecePosition firstPosition;

  /**
   * Constructor for this controller holds
   * the board and the view to control the
   * game state.
   * @param board game board
   * @param view view to render game
   */
  public ChessFeaturesController(Board board, BoardGUIView view) {
    this.board = board;
    this.view = view;
    this.gameRunning = true;
    this.firstMoveMade = false;
    this.firstPosition = null;
  }

  // Processes the move sent by the ChessBoardPanel.
  // Tries to make the move. If the move is successful,
  // checks the state of the game and calls the view
  // to print messages or end the game appropriately.
  @Override
  public void processMove(PiecePosition pos) {
    if (firstMoveMade) {
      try {
        this.board.move(firstPosition, pos);
        switch (this.board.isGameOver()) {
          case IN_PROGRESS -> {
            if (this.board.isKingInCheck(Player.ONE)) {
              this.view.renderMessage("Player one's king is in check!");
            } else if (this.board.isKingInCheck(Player.TWO)) {
              this.view.renderMessage("Player two's king is in check!");
            }
          }
          case P1_WINNER -> {
            this.view.renderMessage("Player 1 wins!!!");
            this.gameRunning = false;
          }
          case P2_WINNER -> {
            this.view.renderMessage("Player 2 wins!!!");
            this.gameRunning = false;
          }
          case STALEMATE -> {
            this.view.renderMessage("Game ends in stalemate!");
            this.gameRunning = false;
          }
        }
      } catch (IllegalArgumentException e) {
        this.view.renderMessage(e.getMessage());
      }
    } else {
      this.view.highlightSquare(pos);
      firstMoveMade = true;
      firstPosition = pos;
    }
    this.view.setBoard(this.board);
    this.view.refresh();
  }

  // Returns true if the game is currently
  // running and false otherwise.
  @Override
  public boolean gameRunning() {
    return this.gameRunning;
  }
}
