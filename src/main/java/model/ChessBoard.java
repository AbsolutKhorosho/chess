package model;

import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;

/**
 * Simple class for a chess board.
 *
 * @author Matt Stetter
 */
public class ChessBoard implements Board {
  private Piece[][] board;
  private Player curPlayer;
  private final int height, width;

  /**
   * Generates a chess board.
   * (8x8 grid with traditional setup)
   */
  public ChessBoard() {
    this.height = 8;
    this.width = 8;
    this.initializeBoard();
    curPlayer = Player.ONE;
  }

  // Initializes the board with the classic arrangement
  // of the pieces on the board.
  private void initializeBoard() {
    this.board = new Piece[height][width];
    int rowStart = 0;
    int rowIter = 1;
    Player p = Player.ONE;
    for (int i = 0; i < 2; i++) {
      this.board[rowStart][0] = new Rook(p);
      this.board[rowStart][1] = new Knight(p);
      this.board[rowStart][2] = new Bishop(p);
      this.board[rowStart][3] = new Queen(p);
      this.board[rowStart][4] = new King(p);
      this.board[rowStart][5] = new Bishop(p);
      this.board[rowStart][6] = new Knight(p);
      this.board[rowStart][7] = new Rook(p);
      rowStart += rowIter;
      for (int j = 0; j < width; j++) {
        this.board[rowStart][j] = new Pawn(p);
      }
      rowStart = 7;
      rowIter = -1;
    }
  }

  @Override
  public int getBoardWidth() {
    return this.width;
  }

  @Override
  public int getBoardHeight() {
    return this.height;
  }

  // Returns the piece at the provided position.
  @Override
  public Piece getPieceAt(PiecePosition pos) throws IllegalArgumentException {
    if ((pos.getRow() < 0 || pos.getRow() >= this.height)
      || (pos.getColumn() < 0 || pos.getColumn() >= this.width)) {
      throw new IllegalArgumentException(String.format("Invalid position: (%d, %d)%n",
              pos.getRow(), pos.getColumn()));
    }
    return this.board[pos.getRow()][pos.getColumn()];
  }

  // Returns the player that is currently playing.
  @Override
  public Player getCurrentPlayer() {
    return this.curPlayer;
  }

  // Tries to make the provided move,
  // throws IllegalArgumentException
  // if the move is invalid. Returns
  // the type of move that was made.
  public MoveType move(PiecePosition p1, PiecePosition p2) {


    return MoveType.MOVE;
  }

  // Returns the state of the game
  // as an enum.
  public FinalState isGameOver() {

    return FinalState.P1_WINNER;
  }
}
