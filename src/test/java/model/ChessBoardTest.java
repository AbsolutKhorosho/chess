package model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import model.Board.State;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;
import model.BoardState.Player;
import view.text.ChessBoardTextView;
import view.text.TextView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the ChessBoard
 * class.
 *
 * @author Matt Stetter
 */
public class ChessBoardTest {
  private Board testBoard;

  @Test
  public void correctBoardSetup() {
    testBoard = new ChessBoard(Player.ONE);
    assertEquals(8, testBoard.getBoardHeight());
    assertEquals(8, testBoard.getBoardWidth());
    assertEquals(new Rook(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 0)));
    assertEquals(new Knight(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 1)));
    assertEquals(new Bishop(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 2)));
    assertEquals(new Queen(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 3)));
    assertEquals(new King(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 4)));
    assertEquals(new Bishop(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 5)));
    assertEquals(new Knight(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 6)));
    assertEquals(new Rook(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(0, 7)));
    for (int i = 0; i < 8; i++) {
      assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(1, i)));
    }
    for (int i = 2; i < 6; i++) {
      for (int j = 0; j < 8; j++) {
        assertNull(testBoard.getPieceAt(new ChessPiecePosition(i, j)));
      }
    }
    for (int i = 0; i < 8; i++) {
      assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(6, i)));
    }
    assertEquals(new Rook(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 0)));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 1)));
    assertEquals(new Bishop(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 2)));
    assertEquals(new Queen(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 3)));
    assertEquals(new King(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 4)));
    assertEquals(new Bishop(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 5)));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 6)));
    assertEquals(new Rook(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(7, 7)));
  }

  @Test
  public void getOutOfBounds() {
    testBoard = new ChessBoard(Player.ONE);
    assertThrows(IllegalArgumentException.class, () -> testBoard.getPieceAt(new ChessPiecePosition(-1, 4)));
    assertThrows(IllegalArgumentException.class, () -> testBoard.getPieceAt(new ChessPiecePosition(8, 3)));
    assertThrows(IllegalArgumentException.class, () -> testBoard.getPieceAt(new ChessPiecePosition(3, -3)));
    assertThrows(IllegalArgumentException.class, () -> testBoard.getPieceAt(new ChessPiecePosition(3, 9)));
  }

  @Test
  public void validMoves() {
    testBoard = new ChessBoard(Player.ONE);
    testBoard.move(new ChessPiecePosition(1, 4), new ChessPiecePosition(3, 4));
    assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(3, 4)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(1, 4)));
    assertThrows(IllegalArgumentException.class, () -> testBoard.move(new ChessPiecePosition(1, 7), new ChessPiecePosition(2, 7)));
    assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(1, 7)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(2, 7)));
    testBoard.move(new ChessPiecePosition(7, 1), new ChessPiecePosition(5, 2));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(5, 2)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(7, 1)));
  }

  @Test
  public void checkMate() {
    testBoard = new ChessBoard(Player.TWO);
    testBoard.move(new ChessPiecePosition(6, 5), new ChessPiecePosition(5, 5));
    assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(5, 5)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(6, 5)));
    testBoard.move(new ChessPiecePosition(1, 4), new ChessPiecePosition(3, 4));
    assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(3, 4)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(1, 4)));
    testBoard.move(new ChessPiecePosition(6, 6), new ChessPiecePosition(4, 6));
    assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(4, 6)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(6, 6)));
    testBoard.move(new ChessPiecePosition(0, 3), new ChessPiecePosition(4, 7));
    assertEquals(new Queen(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(4, 7)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(0, 3)));
    assertEquals(State.P1_WINNER, testBoard.isGameOver());
  }

  @Test
  public void xqcCheckmate() {
    testBoard = new ChessBoard(Player.TWO);
    testBoard.move(new ChessPiecePosition(6, 4), new ChessPiecePosition(4, 4));
    assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(4, 4)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(6, 4)));
    testBoard.move(new ChessPiecePosition(1, 4), new ChessPiecePosition(3, 4));
    assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(3, 4)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(1, 4)));
    testBoard.move(new ChessPiecePosition(7, 6), new ChessPiecePosition(5, 5));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(5, 5)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(7, 6)));
    testBoard.move(new ChessPiecePosition(0, 1), new ChessPiecePosition(2, 2));
    assertEquals(new Knight(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(2, 2)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(0, 1)));
    testBoard.move(new ChessPiecePosition(6, 3), new ChessPiecePosition(4, 3));
    assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(4, 3)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(6, 3)));
    testBoard.move(new ChessPiecePosition(3, 4), new ChessPiecePosition(4, 3));
    assertEquals(new Pawn(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(4, 3)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(3, 4)));
    testBoard.move(new ChessPiecePosition(5, 5), new ChessPiecePosition(4, 3));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(4, 3)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(5, 5)));
    testBoard.move(new ChessPiecePosition(0, 5), new ChessPiecePosition(3, 2));
    assertEquals(new Bishop(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(3, 2)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(0, 5)));
    testBoard.move(new ChessPiecePosition(6, 2), new ChessPiecePosition(5, 2));
    assertEquals(new Pawn(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(5, 2)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(6, 2)));
    testBoard.move(new ChessPiecePosition(0, 3), new ChessPiecePosition(2, 5));
    assertEquals(new Queen(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(2, 5)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(0, 3)));
    testBoard.move(new ChessPiecePosition(4, 3), new ChessPiecePosition(2, 2));
    assertEquals(new Knight(Player.TWO), testBoard.getPieceAt(new ChessPiecePosition(2, 2)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(4, 3)));
    testBoard.move(new ChessPiecePosition(2, 5), new ChessPiecePosition(6, 5));
    assertEquals(new Queen(Player.ONE), testBoard.getPieceAt(new ChessPiecePosition(6, 5)));
    assertNull(testBoard.getPieceAt(new ChessPiecePosition(2, 5)));
    assertEquals(State.P1_WINNER, testBoard.isGameOver());
  }

  //TODO: Add more checkmate scenarios
}