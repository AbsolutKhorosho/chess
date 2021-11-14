package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;

/**
 * Class representing a knight chess
 * piece.
 *
 * @author Matt Stetter
 */
public class Knight extends AbstractChessPiece {

  /**
   * Sets piece type and player
   * that owns piece.
   *
   * @param player owner
   */
  public Knight(Player player) {
    super(PieceType.KNIGHT, player);
  }

  // Returns true if the knight move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    if (p1.equals(p2) || board.getPieceAt(p1) == null) {
      return false;
    }
    Piece takePiece = board.getPieceAt(p2);
    return ((takePiece == null || takePiece.getPlayer() != this.getPlayer())
            && ((Math.abs(p1.getRow() - p2.getRow()) == 2 && Math.abs(p1.getColumn() - p2.getColumn()) == 1)
            ^ (Math.abs(p1.getRow() - p2.getRow()) == 1 && Math.abs(p1.getColumn() - p2.getColumn()) == 2)));
  }
}
