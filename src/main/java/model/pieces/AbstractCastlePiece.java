package model.pieces;

import model.BoardState;

/**
 * Class for the Rook and King
 * to store state about whether
 * a castle move is possible.
 *
 * @author Matt Stetter
 */
public abstract class AbstractCastlePiece extends AbstractChessPiece {

  protected boolean hasMoved;

  public AbstractCastlePiece(PieceType type, BoardState.Player player) {
    super(type, player);
    this.hasMoved = false;
  }

  public boolean hasMoved() {
    return this.hasMoved;
  }
}
