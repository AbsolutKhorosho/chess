package model.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BoardState;
import model.BoardState.Player;
import model.ChessBoard;

import static org.mockito.Mockito.mock;

/**
 * Test class for the Pawn piece
 * class.
 *
 * @author Matt Stetter
 */
public class PawnTest {
  private Piece playerOnePawn;
  private Piece playerTwoPawn;
  private BoardState mockBoard;

  // PLAYER ONE TESTS

  @Test
  public void gameStartDoubleMove() {
    mockBoard = mock(ChessBoard.class);
  }
}
