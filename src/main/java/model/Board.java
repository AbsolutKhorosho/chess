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
   * @throws IllegalArgumentException if the specified move
   *         is invalid.
   */
  void move(PiecePosition p1, PiecePosition p2) throws IllegalArgumentException;

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
  State isGameOver();

  enum State {
    IN_PROGRESS,
    P1_WINNER,
    P2_WINNER,
    STALEMATE
  }

  /**
   * Returns true if the player's king
   * is in check.
   * @param p player
   * @return true if in check
   */
  boolean isKingInCheck(Player p);
}
