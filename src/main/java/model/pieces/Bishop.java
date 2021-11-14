package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.PiecePosition;

/**
 * Class for a Bishop chess piece.
 *
 * @author Matt Stetter
 */
public class Bishop extends AbstractChessPiece {

  /**
   * Constructor sets the player
   * that owns the piece.
   * @param player owner of piece
   */
  public Bishop(Player player) {
    super(PieceType.BISHOP, player);
  }

  // Returns true if the specified bishop move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    return isValidDiagonalMove(p1, p2, board);
  }
}
