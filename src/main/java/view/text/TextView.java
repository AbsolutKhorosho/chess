package view.text;

import java.io.IOException;

import model.BoardState;

/**
 * Interface for a simple view
 * that prints out the state
 * of a board or a message.
 *
 * @author Matt Stetter
 */
public interface TextView {

  /**
   * Renders the message to the
   * initialized destination.
   * @param message the message to render
   * @throws IOException if the message could not
   *                     be rendered
   */
  void renderMessage(String message) throws IOException;

  /**
   * Renders the current state of
   * the board with the following symbols:
   *
   *      P/p: pawn
   *      R/r: rook
   *      H/h: knight
   *      B/b: bishop
   *      Q/q: queen
   *      K/k: king
   *      o: empty space
   * @throws IOException if the board could
   *                     not be rendered
   */
  void renderBoard() throws IOException;

  /**
   * Called by the constructor to update
   * the board to the new version once
   * the game is over.
   * @param board the new board
   */
  void updateBoard(BoardState board);
}
