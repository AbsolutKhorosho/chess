package model.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BoardState;
import model.ChessBoard;

import static org.mockito.Mockito.mock;

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
}
