package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.PiecePosition;
import model.pieces.Piece.PieceType;

/**
 * Abstract super class for a chess piece
 * stores the enum identifying which chess
 * piece it is.
 *
 * @author Matt Stetter
 */
public abstract class AbstractChessPiece implements Piece {
  private PieceType type;
  private Player player;

  /**
   * Sets the piece type and player
   * that owns this piece.
   * @param type piece type
   * @param player owner
   */
  protected AbstractChessPiece(PieceType type, Player player) {
    this.type = type;
    this.player = player;
  }

  // Returns the type of the piece.
  @Override
  public PieceType getType() {
    return this.type;
  }

  // Returns the player that owns the piece.
  @Override
  public Player getPlayer() {
    return this.player;
  }

  // Each piece decides if the move is valid differently.
  @Override
  public abstract boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board);

  // Returns true if the move (rectangular direction) is valid.
  protected boolean isValidRectangularMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    int startRow = p1.getRow();
    int startCol = p1.getColumn();
    int endRow = p2.getRow();
    int endCol = p2.getColumn();
    Piece takePiece = board.getPieceAt(p2);
    return (!pieceInWay(startRow, startCol, endRow, endCol, board)
            && (startRow == endRow ^ startCol == endCol)
            && (takePiece == null || takePiece.getPlayer() != this.getPlayer()));
  }

  // Returns true if there is a piece in the way
  // of the movement between the two.
  private boolean pieceInWay(int startRow, int startCol,
                             int endRow, int endCol, BoardState board) {
    Piece curPiece;
    boolean iterRow = startRow != endRow;
    int iter = iterRow ? (endRow > startRow ? 1 : -1) : (endCol > startCol ? 1 : -1);
    int start = iterRow ? startRow + iter : startCol + iter;
    int end = iterRow ? endRow : endCol;
    for (int i = start; i != end; i += iter) {
      if (iterRow) {
        curPiece = board.getPieceAt(new ChessPiecePosition(i, startCol));
      } else {
        curPiece = board.getPieceAt(new ChessPiecePosition(startRow, i));
      }
      if (curPiece != null) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("%s piece owned by %s",
            this.type, this.player);
  }
}
