package model.pieces;

import net.bytebuddy.pool.TypePool;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.PiecePosition;

/**
 * A class to represent a King chess piece.
 *
 * @author Matt Stetter
 */
public class King extends AbstractCastlePiece {
  /**
   * Constructor sets the player owner
   * and the King piece type.
   * @param player owner of the piece
   */
  public King(Player player) {
    super(PieceType.KING, player);
  }

  // Returns true if the provided move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    Piece takePiece = board.getPieceAt(p2);
    boolean isInCheck = isInCheck(p2, board);
    boolean validSimpleMove = (isAdjacent(p1, p2))
            && !(takePiece != null && takePiece.getPlayer() == this.getPlayer());
    boolean isCastle = isCastle(p1, p2, board);
    return !isInCheck && (validSimpleMove || isCastle);
  }

  private boolean isAdjacent(PiecePosition p1, PiecePosition p2) {
    int rowDistance = Math.abs(p2.getRow() - p1.getRow());
    int colDistance = Math.abs(p2.getColumn() - p1.getColumn());
    return (rowDistance == 1 || rowDistance == 0)
            && (colDistance == 1 || colDistance == 0);
  }

  public boolean isInCheck(PiecePosition pos, BoardState board) {
    Piece checkPiece;
    for (int row = 0; row < board.getBoardHeight(); row++) {
      for (int col = 0; col < board.getBoardWidth(); col++) {
        checkPiece = board.getPieceAt(new ChessPiecePosition(row, col));
        if (checkPiece != null && checkPiece.getType() == PieceType.KING && checkPiece.getPlayer() != this.getPlayer()) {
          if (isAdjacent(new ChessPiecePosition(row, col), pos)) {
            return true;
          } else {
            continue;
          }
        }
        if (checkPiece != null && checkPiece.getPlayer() != this.getPlayer()) {
          if (checkPiece.isValidMove(new ChessPiecePosition(row, col),
                  pos, board)) {
            return true;
          }
        }
      }
    }
    int rowIter = this.getPlayer() == Player.ONE ? 1 : -1;
    Piece leftPiece = null;
    Piece rightPiece = null;
    try {
      leftPiece = board.getPieceAt(new ChessPiecePosition(pos.getRow() + rowIter, pos.getColumn() + 1));
    } catch (IllegalArgumentException ignored) {}
    try {
      rightPiece = board.getPieceAt(new ChessPiecePosition(pos.getRow() + rowIter, pos.getColumn() - 1));
    } catch (IllegalArgumentException ignored) {}
    if (leftPiece == null && rightPiece == null) {
      return false;
    } else if (leftPiece == null) {
      return rightPiece.getPlayer() != this.getPlayer() && rightPiece.getType() == PieceType.PAWN;
    } else if (rightPiece == null) {
      return leftPiece.getPlayer() != this.getPlayer() && leftPiece.getType() == PieceType.PAWN;
    } else {
      return (rightPiece.getPlayer() != this.getPlayer() && rightPiece.getType() == PieceType.PAWN)
              || (leftPiece.getPlayer() != this.getPlayer() && leftPiece.getType() == PieceType.PAWN);
    }
  }

  private boolean isCastle(PiecePosition p1, PiecePosition p2, BoardState board) {
    int rookColumn = (p1.getColumn() - p2.getColumn() == 2) ? 0 : (p1.getColumn() - p2.getColumn() == -2 ? board.getBoardWidth() - 1 : -1);
    if (rookColumn == -1) {
      return false;
    }
    int colIter = rookColumn == 0 ? -1 : 1;
    if (!this.hasMoved
            && !((AbstractCastlePiece)(board.getPieceAt(new ChessPiecePosition(p1.getRow(), rookColumn)))).hasMoved()) {
      for (int col = p1.getColumn() + colIter; col != rookColumn; col += colIter) {
        if (board.getPieceAt(new ChessPiecePosition(p1.getRow(), col)) != null) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
