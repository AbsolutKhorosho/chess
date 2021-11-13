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
 * Test class for the Pawn piece
 * class.
 *
 * @author Matt Stetter
 */
public class PawnTest {
  private static BoardState mockBoard;

  private Piece testPiece;

  @BeforeAll
  public static void setup() {
    mockBoard = mock(ChessBoard.class);
  }

  // PLAYER ONE TESTS

  @Test
  public void gameStartDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void gameStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(2, 4), mockBoard));
  }

  @Test
  public void gameStartInvalidTripleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void gameStartInvalidJumpDouble() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void nonStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void nonStartDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takePiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takeOtherPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.ONE);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }

  @Test
  public void takeMyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }

  @Test
  public void takeMyOtherPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takeEmptyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takeOtherEmptyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }

  @Test
  public void backwardsMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 5), mockBoard));
  }

  @Test
  public void backwardsDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 5))).thenReturn(null);
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(2, 5), mockBoard));
  }

  @Test
  public void backwardsTakeMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void backwardsTakeOtherMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 6))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.ONE);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 6), mockBoard));
  }

  // PLAYER TWO TESTS

  @Test
  public void p2gameStartDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(6, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(6, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void p2gameStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(6, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(6, 4),
            new ChessPiecePosition(5, 4), mockBoard));
  }

  @Test
  public void p2gameStartInvalidTripleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(6, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(6, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void p2gameStartInvalidJumpDouble() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(7, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(6, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(6, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void p2nonStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(5, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void p2nonStartDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void p2takePiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.TWO);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void p2takeOtherPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 6))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.TWO);
    assertTrue(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 6), mockBoard));
  }

  @Test
  public void p2takeMyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 6))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 6), mockBoard));
  }

  @Test
  public void p2takeMyOtherPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(new Pawn(Player.TWO));
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void p2takeEmptyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void p2takeOtherEmptyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 6))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(4, 5),
            new ChessPiecePosition(3, 6), mockBoard));
  }

  @Test
  public void p2backwardsMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 5), mockBoard));
  }

  @Test
  public void p2backwardsDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(5, 5))).thenReturn(null);
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(5, 5), mockBoard));
  }

  @Test
  public void p2backwardsTakeMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void p2backwardsTakeOtherMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.TWO));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(new Pawn(Player.ONE));
    testPiece = new Pawn(Player.TWO);
    assertFalse(testPiece.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }
}
