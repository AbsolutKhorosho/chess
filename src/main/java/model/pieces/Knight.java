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
    int startRow = p1.getRow();
    int startCol = p1.getColumn();
    int endRow = p2.getRow();
    int endCol = p2.getColumn();
    Piece takePiece = board.getPieceAt(p2);
    return ((takePiece == null || takePiece.getPlayer() != this.getPlayer())
            && ((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1)
            ^ (Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2)));
  }
}
