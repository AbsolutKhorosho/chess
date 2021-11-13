package model.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BoardState;
import model.BoardState.Player;

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

  @BeforeAll
  public void setup() {
    playerOnePawn = new Pawn(Player.ONE);
    playerTwoPawn = new Pawn(Player.TWO);
  }

  // PLAYER ONE TESTS

  @Test
  public void gameStartDoubleMove() {
    
  }
}
