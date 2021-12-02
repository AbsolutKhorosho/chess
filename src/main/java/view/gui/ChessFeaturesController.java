package view.gui;

import model.Board;
import model.Board.State;
import model.PiecePosition;

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

  public ChessFeaturesController(Board board, BoardGUIView view) {
    this.board = board;
    this.view = view;
    this.gameRunning = true;
    this.firstMoveMade = false;
  }

  // Processes the move sent by the ChessBoardPanel.
  // Tries to make the move. If the move is successful,
  // checks the state of the game and calls the view
  // to print messages or end the game appropriately.
  @Override
  public void processMove(PiecePosition pos) {

  }

  // Returns true if the game is currently
  // running and false otherwise.
  @Override
  public boolean gameRunning() {
    return this.gameRunning;
  }
}
