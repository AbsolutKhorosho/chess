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
 * Test class for the Knight piece
 * class.
 *
 * @author Matt Stetter
 */
public class KnightTest {
  private static BoardState model;

  private Piece testPiece;

  @BeforeAll
  public static void setup() {
    model = mock(ChessBoard.class);
  }

  @Test
  public void firstMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 3))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(6, 3), model));
  }

  @Test
  public void secondMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 2))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 2), model));
  }

  @Test
  public void thirdMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 2))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(3, 2), model));
  }

  @Test
  public void fourthMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 3))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(2, 3), model));
  }

  @Test
  public void fifthMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(2, 5), model));
  }

  @Test
  public void sixthMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(3, 6))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(3, 6), model));
  }

  @Test
  public void seventhMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 6))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 6), model));
  }

  @Test
  public void eighthMoveDirection() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(6, 5))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(6, 5), model));
  }

  @Test
  public void takeEnemyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 2))).thenReturn(new Knight(Player.TWO));
    testPiece = new Knight(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 2), model));
  }

  @Test
  public void takeMyPiece() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Knight(Player.ONE));
    when(model.getPieceAt(new ChessPiecePosition(5, 2))).thenReturn(new Knight(Player.ONE));
    testPiece = new Knight(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 2), model));
  }

  @Test
  public void moveFromEmpty() {
    when(model.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(model.getPieceAt(new ChessPiecePosition(5, 2))).thenReturn(null);
    testPiece = new Knight(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 4),
            new ChessPiecePosition(5, 2), model));
  }
}
