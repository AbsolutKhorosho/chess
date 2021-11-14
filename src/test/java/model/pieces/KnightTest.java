package model.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BoardState;
import model.BoardState.Player;
import model.ChessBoard;
import model.ChessPiecePosition;

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

  }
}
