package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;

/**
 * Represents a piece. Has the ability
 * to decide if the piece move is valid.
 *
 * @author Matt Stetter
 */
public interface Piece {

  /**
   * Returns the player that
   * owns this piece.
   * @return player who owns this piece
   */
  Player getPlayer();

  /**
   * Returns true if the move from the first
   * position to the second position is valid.
   * @param p1 first position
   * @param p2 second position
   * @param board the state of the board
   * @return true if valid move, false otherwise
   */
  boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board);

  /**
   * Returns the type of the piece using
   * the PieceType enum.
   * @return type of the piece
   */
  PieceType getType();

  /**
   * Enum list of possible piece used
   * in the chess game
   */
  enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING
  }
}
