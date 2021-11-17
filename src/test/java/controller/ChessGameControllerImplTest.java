package controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import model.Board;
import model.BoardState;
import model.BoardState.Player;
import model.ChessBoard.ChessBoardBuilder;
import view.text.TextView;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the ChessGame controller
 * class.
 *
 * @author Matt Stetter
 */
public class ChessGameControllerImplTest {
  private Board mockModel;
  private TextView mockView;
  private ChessGameController CuT;
  private StringBuilder log;

  private class MockView implements TextView {

    @Override
    public void renderMessage(String message) throws IOException {
      log.append(message);
    }

    @Override
    public void renderBoard() throws IOException {
      log.append("|render board|");
    }

    @Override
    public void updateBoard(BoardState board) {
      log.append("|update board|");
    }
  }

  @Test
  public void welcomeMessage() {
    log = new StringBuilder();
    Readable input = new StringReader("quit\n");
    mockView = new MockView();
    mockModel = new ChessBoardBuilder().build();
    CuT = new ChessGameControllerImpl(mockView, input);
    CuT.play();
    assertEquals("""
            Welcome to Chess
            By: Matt Stetter
            A move is made by entering the starting
            position (A1-H8) and then the ending position
            using the same scheme.
            Enter "start" to start the game
            or "help" to get a list of commands.
            Thanks for playing!
            """, log.toString());
  }

  @Test
  public void playDefaultGame() {
    log = new StringBuilder();
    Readable input = new StringReader(String.format("start%sF2 F3%sE7 E5%sG2 G4%sD8 H4%squit",
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator()));
    mockView = new MockView();
    mockModel = new ChessBoardBuilder().player(Player.TWO).build();
    CuT = new ChessGameControllerImpl(mockView, input);
    CuT.play();
    assertEquals("""
            Welcome to Chess
            By: Matt Stetter
            A move is made by entering the starting
            position (A1-H8) and then the ending position
            using the same scheme.
            Enter "start" to start the game
            or "help" to get a list of commands.
            |render board|Player 2 enter a move: |update board|Player 1 enter a move: |render board|Player 2 enter a move: |render board|Player 1 enter a move: |render board|GAME OVER!
            ---------------------------------
            Type start to play again
            Thanks for playing!
            """, log.toString());
  }




  // TODO: NoSuchElementException tests

}
