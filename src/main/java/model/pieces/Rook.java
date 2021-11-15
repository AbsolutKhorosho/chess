package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;

/**
 * Class for a rook chess piece.
 *
 * @author Matt Stetter
 */
public class Rook extends AbstractCastlePiece {

  /**
   * Sets the piece type and player
   * that owns this piece.
   * @param player owner
   */
  public Rook(Player player) {
    super(PieceType.ROOK, player);
  }

  // Returns true if the rook move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    boolean validMove = isValidRectangularMove(p1, p2, board);
    if (validMove) {
      this.hasMoved = true;
    }
    return validMove;
  }
}
