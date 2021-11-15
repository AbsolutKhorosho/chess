package model.pieces;

import org.junit.jupiter.api.Test;

import model.BoardState;
import model.BoardState.Player;
import model.ChessBoard;
import model.ChessPiecePosition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the King chess
 * piece.
 *
 * @author Matt Stetter
 */
public class KingTest {
  private static BoardState model;

  private Piece testPiece;

  // SIMPLE MOVE TEST

  @Test
  public void forwardTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void upRightTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 3))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 3), model));
  }

  @Test
  public void rightTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 3), model));
  }

  @Test
  public void downRightTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(0, 3), model));
  }

  @Test
  public void backTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(0, 4), model));
  }

  @Test
  public void downLeftTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(0, 5), model));
  }

  @Test
  public void leftTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(1, 5), model));
  }

  @Test
  public void upLeftTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(null);
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 5), model));
  }

  @Test
  public void takeEnemy() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(new Pawn(Player.TWO));
    testPiece = new King(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 5), model));
  }

  @Test
  public void takeMyPiece() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new King(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(new Pawn(Player.ONE));
    testPiece = new King(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 5), model));
  }

  // CHECK MOVES

  @Test
  public void rookPutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(4, 0))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(3, 0))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 0))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 0))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 0))).thenReturn(null);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 1))).thenReturn(null);
    when(model.getBoardWidth()).thenReturn(2);
    when(model.getBoardHeight()).thenReturn(5);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 1),
            new ChessPiecePosition(1, 0), model));
  }

  @Test
  public void pawnPutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(2, 0))).thenReturn(new Pawn(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(1, 0))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 0))).thenReturn(null);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 1))).thenReturn(null);
    when(model.getBoardWidth()).thenReturn(2);
    when(model.getBoardHeight()).thenReturn(3);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 1),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void bishopPutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(3, 7))).thenReturn(new Bishop(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(testPiece);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(4);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(0, 4), model));
  }

  @Test
  public void knightPutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Knight(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(testPiece);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 5),
            new ChessPiecePosition(3, 5), model));
  }

  @Test
  public void queenPutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Queen(Player.TWO));
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(testPiece);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(1, 5),
            new ChessPiecePosition(1, 4), model));
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 5),
            new ChessPiecePosition(1, 6), model));
  }

  @Test
  public void myPiecePutKingInCheck() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Queen(Player.ONE));
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(1, 5))).thenReturn(testPiece);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 5),
            new ChessPiecePosition(1, 4), model));
  }

  // CASTLE MOVES

  @Test
  public void rightCastleTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 0))).thenReturn(new Rook(Player.ONE));
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 2), model));
  }

  @Test
  public void leftCastleTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Rook(Player.ONE));
    when(model.getBoardWidth()).thenReturn(8);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 6), model));
  }

  @Test
  public void p2rightCastleTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.TWO);
    when(model.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(7, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(new Rook(Player.TWO));
    when(model.getBoardWidth()).thenReturn(8);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(7, 4),
            new ChessPiecePosition(7, 6), model));
  }

  @Test
  public void p2leftCastleTest() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.TWO);
    when(model.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(7, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 0))).thenReturn(new Rook(Player.TWO));
    when(model.getBoardWidth()).thenReturn(8);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(7, 4),
            new ChessPiecePosition(7, 2), model));
  }

  @Test
  public void castleMyPieceInWay() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Rook(Player.ONE));
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 6), model));
  }

  @Test
  public void castleEnemyPieceInWay() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Knight(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 0))).thenReturn(new Rook(Player.ONE));
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 2), model));
  }

  @Test
  public void castleAfterMove() {
    model = mock(ChessBoard.class);
    when(model.getBoardWidth()).thenReturn(8);
    when(model.getBoardHeight()).thenReturn(8);
    testPiece = new King(Player.ONE);
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(testPiece);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Rook(Player.ONE));
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(1, 4), model));
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 2), model));
  }
}
