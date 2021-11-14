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
 * Test class for the Rook piece
 * class.
 *
 * @author Matt Stetter
 */
public class RookTest {
  private static BoardState model;

  private Piece testPiece;

  @BeforeAll
  public static void setup() {
    model = mock(ChessBoard.class);
  }

  @Test
  public void forwardMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void rightMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void leftMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void backMove() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void blockedForwardMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void blockedRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void blockedLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void blockedBackMove() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void upRightDiagonal() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(6, 6), model));
  }

  @Test
  public void upLeftDiagonal() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(6, 4), model));
  }

  @Test
  public void downRightDiagonal() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(4, 6), model));
  }

  @Test
  public void downLeftDiagonal() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void forwardEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void leftEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void forwardTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Rook(Player.TWO));
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Rook(Player.TWO));
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(new Rook(Player.TWO));
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Rook(Player.TWO));
    testPiece = new Rook(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void forwardTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Rook(Player.ONE));
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Rook(Player.ONE));
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(new Rook(Player.ONE));
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Rook(Player.ONE));
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void forwardEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Rook(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Rook(Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void moveFromEmpty() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Rook(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(4, 4), model));
  }
}
