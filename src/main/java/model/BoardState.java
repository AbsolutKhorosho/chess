package model;

import model.pieces.Piece;

/**
 * An interface for the state of a
 * board game (chess/checkers).
 *
 * @author Matt Stetter
 */
public interface BoardState {

  /**
   * Gets the piece at the current
   * position of the board using
   * rectangular coordinates.
   * @param pos position of the board
   * @return chess piece
   */
  Piece getPieceAt(PiecePosition pos);

  /**
   * Returns the current player
   * who is playing using the
   * Player enum.
   * @return current player
   */
  Player getCurrentPlayer();

  /**
   * Enum for the two players
   * playing the chess game
   */
  enum Player {
    ONE,
    TWO
  }
}
