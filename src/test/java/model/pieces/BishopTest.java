package model.pieces;

import org.junit.jupiter.api.BeforeAll;
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
 * Test class for the Bishop piece
 * class.
 *
 * @author Matt Stetter
 */
public class BishopTest {
  private static BoardState model;

  private Piece testPiece;

  @BeforeAll
  public static void setup() {
    model = mock(ChessBoard.class);
  }

  @Test
  public void upRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void downRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void upLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void upRightMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void upRightEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(new Bishop(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void downRightMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downRightEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(new Bishop(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downLeftMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void downLeftEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(new Bishop(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void upLeftMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void upLeftEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Bishop(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void takeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(new Bishop(Player.ONE));
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void takeEnemyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Bishop(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(new Bishop(Player.TWO));
    testPiece = new Bishop(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void moveFromEmpty() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    testPiece = new Bishop(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 5), model));
  }
}
