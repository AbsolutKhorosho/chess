package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;

/**
 * Class for a queen chess piece.
 *
 * @author Matt Stetter
 */
public class Queen extends AbstractChessPiece {

  /**
   * Sets the player that owns the
   * piece and the queen enum value.
   * @param player player that owns piece
   */
  public Queen(Player player) {
    super(PieceType.QUEEN, player);
  }

  // Returns true if the specified move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    return (isValidRectangularMove(p1, p1, board)
           || isValidDiagonalMove(p1, p2, board));
  }
}
