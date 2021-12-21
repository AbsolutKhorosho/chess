package view.gui;

import model.PiecePosition;

/**
 * Interface for a GUI board that
 * can display messages and refresh
 * the current graphics.
 *
 * @author Matt Stetter
 */
public interface BoardGUIView {

  /**
   * Refreshes the graphics
   * on the screen.
   */
  void refresh();

  /**
   * Renders the specified message
   * to a position on the screen.
   * @param message message to render
   */
  void renderMessage(String message);

  /**
   * Controller calls GUI to highlight a square
   * when it is the first part of a move.
   * @param pos position to highlight
   */
  void highlightSquare(PiecePosition pos);
}
