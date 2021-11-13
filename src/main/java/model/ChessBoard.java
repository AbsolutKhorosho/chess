package model;

import model.pieces.Piece;

/**
 * Simple class for a chess board.
 *
 * @author Matt Stetter
 */
public class ChessBoard implements Board {
  private Piece[][] board;
  private Player curPlayer;

  /**
   * Generates a chess board.
   * (8x8 grid with traditional setup)
   */
  public ChessBoard() {
    this.initializeBoard();
    curPlayer = Player.ONE;
  }

  // Initializes the board with the classic arrangement
  // of the pieces on the board.
  private void initializeBoard() {

  }

  // Returns the piece at the provided position.
  @Override
  public Piece getPieceAt(PiecePosition pos) {
    return null;
  }

  // Returns the player that is currently playing.
  @Override
  public Player getCurrentPlayer() {
    return this.curPlayer;
  }
}
