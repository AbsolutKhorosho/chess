package view.gui;

import model.PiecePosition;

/**
 * A controller that manages
 * interactions between GUI and
 * model.
 *
 * @author Matt Stetter
 */
public interface FeaturesController {

  /**
   * Processes each position of the
   * move from the GUI.
   * @param pos move position
   */
  void processMove(PiecePosition pos);

  /**
   * Returns true if the game is currently
   * running, and false otherwise.
   * @return true if running, false otherwise
   */
  boolean gameRunning();
}
