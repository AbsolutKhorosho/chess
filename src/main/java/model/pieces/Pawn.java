package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.PiecePosition;

/**
 * Class for a pawn that decides
 * if a move is valid.
 *
 * @author Matt Stetter
 */
public class Pawn extends AbstractChessPiece {

  // Passes type and player owner to abstract constructor.
  public Pawn(Player player) {
    super(PieceType.PAWN, player);
  }

  // Returns true if the pawn move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    int startRow = p1.getRow();
    int startCol = p1.getColumn();
    int endRow = p2.getRow();
    int endCol = p2.getColumn();
    int playerIter = this.getPlayer() == Player.ONE ? 1 : -1;
    int pawnStart = this.getPlayer() == Player.ONE ? 1 : 6;
    Piece takePiece = board.getPieceAt(p2);
    if (startCol == endCol) {
      if (startRow == pawnStart) {
        return ((takePiece == null) &&
                ((endRow == pawnStart + playerIter) ||
                (endRow == pawnStart + (2 * playerIter) && board.getPieceAt(
                        new ChessPiecePosition(endRow + playerIter, endCol)) == null)));
      } else {
        return endRow == startRow + playerIter;
      }
    } else if (endCol == startCol + 1
              || endCol == startCol - 1) {
      return (endRow == startRow + playerIter
              && takePiece.getType() != null
              && takePiece.getPlayer() != this.getPlayer());
    } else {
      return false;
    }
  }
}
