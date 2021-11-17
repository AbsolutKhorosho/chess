package view.gui;

import model.Board;

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
   *
   * @param board
   */
  void setBoard(Board board);
}
