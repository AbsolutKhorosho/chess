package controller;

/**
 * A simple interface for the play
 * method that starts the controller
 * playing the game.
 *
 * @author Matt Stetter
 */
public interface ChessGameController {

  /**
   * Method starts the game loop
   * and continues until checkmate
   * or stalemate occurs.
   */
  void play();

}
