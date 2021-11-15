package model;

/**
 * An interface for an interactive chess/checkers
 * board.
 *
 * @author Matt Stetter
 */
public interface Board extends BoardState {

  /**
   * Tries to move the piece to the
   * specified position. IllegalArgumentException
   * is thrown if the move is invalid.
   * @param p1 the starting position
   * @param p2 the ending position
   * @return INVALID if the move is invalid
   *         MOVE if a move to an empty space
   *         CAPTURE if the move is a capture move
   *         CASTLE if the move is a castle
   */
  MoveType move(PiecePosition p1, PiecePosition p2) throws IllegalArgumentException;

  enum MoveType {
    INVALID,
    MOVE,
    CAPTURE,
    CASTLE
  }

  /**
   * Returns whether the game is over.
   * A different enum value
   * depending on the final state
   * of the game.
   * @return IN_PROGRESS while the game is going
   *         P1_WINNER if player 1 wins
   *         P2_WINNER if player 2 wins
   *         STALEMATE if the game is a stalemate
   */
  FinalState isGameOver();

  enum FinalState {
    IN_PROGRESS,
    P1_WINNER,
    P2_WINNER,
    STALEMATE
  }
}
