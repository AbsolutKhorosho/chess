package view.gui;

import model.BoardState;
import model.PiecePosition;

/**
 * A panel on the window
 * to show the board of the
 * game.
 *
 * @author Matt Stetter
 */
public interface BoardPanel {

  /**
   * Sets the controller to
   * the chess board panel so
   * the mouse click listener
   * can call the process move
   * method.
   * @param controller the controller to set
   */
  void setController(FeaturesController controller);

  /**
   * Highlights the square on the board.
   * @param pos position to highlight
   */
  void highlight(PiecePosition pos);
}
