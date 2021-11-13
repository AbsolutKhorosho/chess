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

  private Piece playerOne;
  private Piece playerTwo;

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
    playerOne = new Pawn(Player.ONE);
    assertTrue(playerOne.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void gameStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    playerOne = new Pawn(Player.ONE);
    assertTrue(playerOne.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(2, 4), mockBoard));
  }

  @Test
  public void gameStartInvalidTripleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    playerOne = new Pawn(Player.ONE);
    assertFalse(playerOne.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void gameStartInvalidJumpDouble() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(0, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(1, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    playerOne = new Pawn(Player.ONE);
    assertFalse(playerOne.isValidMove(new ChessPiecePosition(1, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void nonStartSingleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    playerOne = new Pawn(Player.ONE);
    assertTrue(playerOne.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(3, 4), mockBoard));
  }

  @Test
  public void nonStartDoubleMove() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(2, 4))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 4))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(null);
    playerOne = new Pawn(Player.ONE);
    assertFalse(playerOne.isValidMove(new ChessPiecePosition(2, 4),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takePiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 4))).thenReturn(new Pawn(Player.TWO));
    playerOne = new Pawn(Player.ONE);
    assertTrue(playerOne.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 4), mockBoard));
  }

  @Test
  public void takeOtherPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(new Pawn(Player.TWO));
    playerOne = new Pawn(Player.ONE);
    assertTrue(playerOne.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }

  @Test
  public void takeMyPiece() {
    when(mockBoard.getPieceAt(new ChessPiecePosition(3, 5))).thenReturn(new Pawn(Player.ONE));
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 5))).thenReturn(null);
    when(mockBoard.getPieceAt(new ChessPiecePosition(4, 6))).thenReturn(new Pawn(Player.ONE));
    playerOne = new Pawn(Player.ONE);
    assertFalse(playerOne.isValidMove(new ChessPiecePosition(3, 5),
            new ChessPiecePosition(4, 6), mockBoard));
  }
}
