package controller;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.CharBuffer;

import model.Board;
import model.BoardState;
import model.BoardState.Player;
import model.ChessBoard.ChessBoardBuilder;
import view.text.TextView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

  private static final String welcomeMessage = """
          Welcome to Chess
          By: Matt Stetter
          A move is made by entering the starting
          position (A1-H8) and then the ending position
          using the same scheme.
          Enter "start" to start the game
          or "help" to get a list of commands.
          """;

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
    assertEquals(welcomeMessage + """
            Thanks for playing!
            """, log.toString());
  }

  @Test
  public void playDefaultGame() {
    log = new StringBuilder();
    InputStream input = new ByteArrayInputStream("start -p 2\nF2 F3 E7 E5 G2 G4 D8 H4\nquit".getBytes());
    Readable in = new InputStreamReader(input);
    mockView = new MockView();
    mockModel = new ChessBoardBuilder().player(Player.TWO).build();
    CuT = new ChessGameControllerImpl(mockView, in);
    CuT.play();
    assertEquals(welcomeMessage + """
            |update board||render board|Player 2 enter a move: |render board|Player 1 enter a move: |render board|Player 2 enter a move: |render board|Player 1 enter a move: |render board|GAME OVER!
            ---------------------------------
            Type start to play again
            Thanks for playing!
            """, log.toString());
  }

  @Test
  public void quitStartGame() {
    log = new StringBuilder();
    InputStream input = new ByteArrayInputStream("start\nquit\nquit".getBytes());
    Readable in = new InputStreamReader(input);
    mockView = new MockView();
    mockModel = new ChessBoardBuilder().build();
    CuT = new ChessGameControllerImpl(mockView, in);
    CuT.play();
    assertEquals(welcomeMessage + """
            |update board||render board|Player 1 enter a move: |render board|GAME OVER!
            ---------------------------------
            Type start to play again
            Thanks for playing!
            """, log.toString());
  }
}
