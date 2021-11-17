package model;

import java.util.ArrayList;
import java.util.List;

import model.pieces.AbstractCastlePiece;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Piece.PieceType;
import model.pieces.Queen;
import model.pieces.Rook;

/**
 * Simple class for a chess board.
 *
 * @author Matt Stetter
 */
public class ChessBoard implements Board {
  private Piece[][] board;
  private List<Piece> p1Taken;
  private List<Piece> p2Taken;
  private King p1King;
  private PiecePosition p1Position;
  private King p2King;
  private PiecePosition p2Position;
  private Player curPlayer;
  private final int height, width;
  private State finalState;

  /**
   * Generates a chess board.
   * (8x8 grid with traditional setup)
   */
  private ChessBoard(Player startingPlayer, int height, int width) {
    this.height = height;
    this.width = width;
    this.initializeBoard();
    this.curPlayer = startingPlayer;
    this.finalState = State.IN_PROGRESS;
  }

  // Initializes the board with the classic arrangement
  // of the pieces on the board.
  private void initializeBoard() {
    this.board = new Piece[height][width];
    this.p1Taken = new ArrayList<>();
    this.p2Taken = new ArrayList<>();
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
      p = Player.TWO;
    }
    this.p1King = (King)this.board[0][4];
    this.p1Position = new ChessPiecePosition(0, 4);
    this.p2King = (King)this.board[7][4];
    this.p2Position = new ChessPiecePosition(7, 4);
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
  @Override
  public void move(PiecePosition p1, PiecePosition p2)
      throws IllegalArgumentException {
    if (finalState != State.IN_PROGRESS) {
      return;
    }
    Piece movingPiece = this.getPieceAt(p1);
    if (movingPiece == null) {
      throw new IllegalArgumentException("Cannot move from empty space");
    }
    Piece takePiece = this.getPieceAt(p2);
    MoveType type;
    if (movingPiece.isValidMove(p1, p2, this)) {
      if (movingPiece.getPlayer() != this.curPlayer) {
        throw new IllegalArgumentException("Cannot move the opponent's piece");
      } else if (isKingInCheck(movingPiece.getPlayer())) {
        if (movingPiece instanceof King) {
          type = MoveType.MOVE;
        } else {
          King playerKing = movingPiece.getPlayer() == Player.ONE ? p1King : p2King;
          PiecePosition kingPosition = movingPiece.getPlayer() == Player.ONE ? p1Position : p2Position;
          this.makeMove(movingPiece, MoveType.MOVE, p1, p2);
          if (playerKing.isInCheck(kingPosition, this)) {
            this.undoMove(movingPiece, p1, p2);
            throw new IllegalArgumentException("Must move king out of check");
          } else {
            this.undoMove(movingPiece, p1, p2);
            type = MoveType.MOVE;
          }
        }
      } else if (movingPiece.getType() == PieceType.KING
              && Math.abs(p2.getColumn() - p1.getColumn()) == 2) {
        type = MoveType.CASTLE;
      } else if (takePiece != null) {
        type = MoveType.CAPTURE;
      } else {
        type = MoveType.MOVE;
      }
    } else {
      throw new IllegalArgumentException("Invalid move");
    }
    this.makeMove(movingPiece, type, p1, p2);
    if (movingPiece instanceof King) {
      if (movingPiece.getPlayer() == Player.ONE) {
        p1Position = new ChessPiecePosition(p2.getRow(), p2.getColumn());
      } else {
        p2Position = new ChessPiecePosition(p2.getRow(), p2.getColumn());
      }
    }
    if (movingPiece instanceof AbstractCastlePiece) {
      ((AbstractCastlePiece) movingPiece).setHasMoved();
    }
    this.curPlayer = this.curPlayer == Player.ONE ? Player.TWO : Player.ONE;
  }

  // Needed to check if the move puts the king
  // out of check. Whether it works,
  // the move is reset after the check.
  private void undoMove(Piece movingPiece, PiecePosition p1, PiecePosition p2) {
    this.board[p1.getRow()][p1.getColumn()] = movingPiece;
    this.board[p2.getRow()][p2.getColumn()] = null;
  }

  // makes the move once it has been
  // verified to be valid.
  private void makeMove(Piece move, MoveType type, PiecePosition p1, PiecePosition p2) {
    switch (type) {
      case CASTLE -> {
        this.board[p2.getRow()][p2.getColumn()] = move;
        this.board[p1.getRow()][p1.getColumn()] = null;
        Piece castleRook;
        if (p2.getColumn() == 2) {
          castleRook = this.board[p2.getRow()][0];
          this.board[p2.getRow()][0] = null;
          this.board[p2.getRow()][3] = castleRook;
        } else {
          castleRook = this.board[p2.getRow()][7];
          this.board[p2.getRow()][7] = null;
          this.board[p2.getRow()][4] = castleRook;
        }
      }
      case CAPTURE -> {
        if (move.getPlayer() == Player.ONE) {
          p2Taken.add(this.board[p2.getRow()][p2.getColumn()]);
        } else {
          p1Taken.add(this.board[p2.getRow()][p2.getColumn()]);
        }
        this.board[p2.getRow()][p2.getColumn()] = move;
        this.board[p1.getRow()][p1.getColumn()] = null;
      }
      default -> {
        this.board[p2.getRow()][p2.getColumn()] = move;
        this.board[p1.getRow()][p1.getColumn()] = null;
      }
    }
  }

  private enum MoveType {
    MOVE,
    CAPTURE,
    CASTLE
  }

  // Returns the state of the game
  // as an enum.
  @Override
  public State isGameOver() {
    boolean p1HasMove = this.playerHasMove(Player.ONE);
    boolean p1InCheck = this.isKingInCheck(Player.ONE);
    boolean p2HasMove = this.playerHasMove(Player.TWO);
    boolean p2InCheck = this.isKingInCheck(Player.TWO);
    if (p1HasMove && p2HasMove) {
      return State.IN_PROGRESS;
    } else if (p1HasMove) {
      this.finalState = State.P1_WINNER;
      return State.P1_WINNER;
    } else if (p2HasMove) {
      this.finalState = State.P2_WINNER;
      return State.P2_WINNER;
    } else if (!p1InCheck || !p2InCheck) {
      this.finalState = State.STALEMATE;
      return State.STALEMATE;
    } else {
      return null;
    }
  }

  private boolean playerHasMove(Player p) {
    Piece cur;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        cur = this.getPieceAt(new ChessPiecePosition(row, col));
        if (cur != null && cur.getPlayer() == p) {
          for (int pRow = 0; pRow < this.height; pRow++) {
            for (int pCol = 0; pCol < this.width; pCol++) {
              if (cur.isValidMove(new ChessPiecePosition(row, col),
                      new ChessPiecePosition(pRow, pCol), this)) {
                if (isKingInCheck(cur.getPlayer())) {
                  MoveType type;
                  King k = this.curPlayer == Player.ONE ? p1King : p2King;
                  Piece pieceAtPos = this.getPieceAt(new ChessPiecePosition(pRow, pCol));
                  if (cur.equals(k)) {
                    if (Math.abs(pCol - col) == 2) {
                      type = MoveType.CASTLE;
                      this.board[pRow][pCol] = k;
                      this.board[row][col] = null;
                      Piece getRook;
                      if (pCol == 6) {
                        getRook = this.board[pRow][7];
                        this.board[pRow][5] = getRook;
                        this.board[pRow][7] = null;
                      } else {
                        getRook = this.board[pRow][0];
                        this.board[pRow][2] = getRook;
                        this.board[pRow][0] = null;
                      }
                    } else {
                      type = MoveType.MOVE;
                      this.board[pRow][pCol] = cur;
                      this.board[row][col] = null;
                    }
                    if (cur.getPlayer() == Player.ONE) {
                      p1Position = new ChessPiecePosition(pRow, pCol);
                    } else {
                      p2Position = new ChessPiecePosition(pRow, pCol);
                    }
                  } else {
                    type = MoveType.MOVE;
                    this.board[pRow][pCol] = cur;
                    this.board[row][col] = null;
                  }
                  if (isKingInCheck(cur.getPlayer())) {
                    undoAllMoves(cur, pieceAtPos, type, new ChessPiecePosition(row, col), new ChessPiecePosition(pRow, pCol));
                    if (cur.getType() == PieceType.KING) {
                      if (cur.getPlayer() == Player.ONE) {
                        p1Position = new ChessPiecePosition(row, col);
                      } else {
                        p2Position = new ChessPiecePosition(row, col);
                      }
                    }
                  } else {
                    if (cur.getType() == PieceType.KING) {
                      if (cur.getPlayer() == Player.ONE) {
                        p1Position = new ChessPiecePosition(row, col);
                      } else {
                        p2Position = new ChessPiecePosition(row, col);
                      }
                    }
                    undoAllMoves(cur, pieceAtPos, type, new ChessPiecePosition(row, col), new ChessPiecePosition(pRow, pCol));
                    return true;
                  }
                } else {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }

  private void undoAllMoves(Piece cur, Piece takePiece, MoveType type, PiecePosition p1, PiecePosition p2) {
    if (type == MoveType.CASTLE) {
      Piece rook;
      if (p2.getColumn() == 6) {
        rook = this.getPieceAt(new ChessPiecePosition(p2.getRow(), 5));
        this.board[p2.getRow()][4] = cur;
        this.board[p2.getRow()][7] = rook;
        this.board[p2.getRow()][5] = null;
        this.board[p2.getRow()][6] = null;
      } else {
        rook = this.getPieceAt(new ChessPiecePosition(p2.getRow(), 2));
        this.board[p2.getRow()][3] = cur;
        this.board[p2.getRow()][0] = rook;
        this.board[p2.getRow()][2] = null;
        this.board[p2.getRow()][1] = null;
      }
    } else {
      this.board[p1.getRow()][p1.getColumn()] = cur;
      this.board[p2.getRow()][p2.getColumn()] = takePiece;
    }
  }

  // Returns true if the specific
  // player's king is in check.
  @Override
  public boolean isKingInCheck(Player p) {
    if (p == Player.ONE) {
      return this.p1King.isInCheck(this.p1Position, this);
    } else {
      return this.p2King.isInCheck(this.p2Position, this);
    }
  }

  // Simple builder class used to generate instances
  // of the ChessBoard class.
  public static class ChessBoardBuilder implements BoardBuilder {
    private Player p;
    private int buildWidth;
    private int buildHeight;

    /**
     * Constructor sets the default
     * values for a ChessBoard to build.
     */
    public ChessBoardBuilder() {
      this.p = Player.ONE;
      this.buildWidth = 8;
      this.buildHeight = 8;
    }

    // Sets the player that starts the game.
    @Override
    public BoardBuilder player(Player p) {
      this.p = p;
      return this;
    }

    // Sets the width of the board.
    @Override
    public BoardBuilder width(int width) {
      this.buildWidth = width;
      return this;
    }

    // Sets the height of the board.
    @Override
    public BoardBuilder height(int height) {
      this.buildHeight = height;
      return this;
    }

    // Builds the Board using the specified
    // parameters.
    @Override
    public Board build() {
      return new ChessBoard(p, buildHeight, buildWidth);
    }
  }
}
