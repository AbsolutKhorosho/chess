package model.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BoardState;
import model.ChessBoard;
import model.ChessPiecePosition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the Queen piece
 * class.
 *
 * @author Matt Stetter
 */
public class QueenTest {
  private static BoardState model;

  private Piece testPiece;

  @BeforeAll
  public static void setup() {
    model = mock(ChessBoard.class);
  }

  // DIAGONAL TESTS

  @Test
  public void upRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void downRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void upLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void upRightMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void upRightEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 2))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(5, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(5, 1), model));
  }

  @Test
  public void downRightMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downRightEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 3))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(2, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 1), model));
  }

  @Test
  public void downLeftMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void downLeftEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 6))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(1, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(1, 7), model));
  }

  @Test
  public void upLeftMyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void upLeftEnemyBlockedMove() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void takeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(new Queen(BoardState.Player.ONE));
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void takeEnemyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(6, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(7, 7))).thenReturn(new Queen(BoardState.Player.TWO));
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(7, 7), model));
  }

  @Test
  public void moveFromEmptySpace() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 5), model));
  }

  // RECTANGULAR TESTS

  @Test
  public void forwardMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void rightMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void leftMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void backMove() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void blockedForwardMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void blockedRightMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void blockedLeftMove() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void blockedBackMove() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void forwardEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(3, 4), model));
  }

  @Test
  public void leftEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backEnemyBlock() {
    when(model.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 5),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void forwardTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.TWO));
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Queen(BoardState.Player.TWO));
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(new Queen(BoardState.Player.TWO));
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backTakePiece() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Queen(BoardState.Player.TWO));
    testPiece = new Queen(BoardState.Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void forwardTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(new Queen(BoardState.Player.ONE));
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(new Queen(BoardState.Player.ONE));
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backTakeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void forwardEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 4),
            new ChessPiecePosition(4, 4), model));
  }

  @Test
  public void leftEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 6))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 7))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 3),
            new ChessPiecePosition(0, 7), model));
  }

  @Test
  public void rightEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(0, 5))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 3))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(0, 2))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(0, 1))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(0, 5),
            new ChessPiecePosition(0, 1), model));
  }

  @Test
  public void backEnemyPieceInWay() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Queen(BoardState.Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Queen(BoardState.Player.TWO));
    when(model.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(1, 4), model));
  }

  @Test
  public void moveFromEmpty() {
    when(model.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Queen(BoardState.Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(4, 4), model));
  }
}
