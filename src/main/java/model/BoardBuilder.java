package model;

import model.BoardState.Player;

/**
 * Interface used for building
 * an instance of the Board interface.
 *
 * @author Matt Stetter
 */
public interface BoardBuilder {

  /**
   * Returns this builder instance
   * with the starting player set
   * using the parameter.
   * @param p the player to start
   * @return this
   */
  BoardBuilder player(Player p);

  /**
   * Returns the builder instance
   * with the specified board width.
   * @param width board width
   * @return this
   */
  BoardBuilder width(int width);

  /**
   * Returns the builder instance
   * with the specified board height.
   * @param height board height
   * @return this
   */
  BoardBuilder height(int height);

  /**
   * Generates the Board instance
   * with the set parameters.
   * @return new Board instance
   */
  Board build();
}
