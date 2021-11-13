package model;

/**
 * Interface for a piece position on the
 * board. Uses algebraic chess notation for
 * the positions.
 *
 * @author Matt Stetter
 */
public interface PiecePosition {

  /**
   * Returns the number row of the
   * position (0 from the top to 7 at
   * the bottom).
   * @return row position
   */
  int getRow();

  /**
   * Returns the number column of the
   * position (0 from the top to 7 at
   * the bottom).
   * @return column position
   */
  int getColumn();
}
