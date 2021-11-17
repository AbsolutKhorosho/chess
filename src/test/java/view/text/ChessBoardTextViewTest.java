package view.text;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import model.Board;
import model.BoardState.Player;
import model.ChessBoard.ChessBoardBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the simple
 * text based view class
 * ChessBoardTextView.
 *
 * @author Matt Stetter
 */
public class ChessBoardTextViewTest {
  private static TextView testView;
  private Board testModel;

  @Test
  public void message() {
    StringBuilder log = new StringBuilder();
    testModel = new ChessBoardBuilder().build();
    testView = new ChessBoardTextView(testModel, log);
    String expected = "Hello, World!\n";
    try {
      testView.renderMessage(expected);
    } catch (IOException e) {
      fail("Could not output message");
    }
    assertEquals(expected, log.toString());
  }

  @Test
  public void render() {
    StringBuilder log = new StringBuilder();
    testModel = new ChessBoardBuilder().build();
    testView = new ChessBoardTextView(testModel, log);
    try {
      testView.renderBoard();
    } catch (IOException e) {
      fail("Could not render board");
    }
    String expected = """
            R H B Q K B H R\s
            P P P P P P P P\s
            o o o o o o o o\s
            o o o o o o o o\s
            o o o o o o o o\s
            o o o o o o o o\s
            p p p p p p p p\s
            r h b q k b h r\s
            """;
    assertEquals(expected, log.toString());
  }

  private class MockAppendable implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }

  @Test
  public void messageIOException() {
    testModel = new ChessBoardBuilder().build();
    Appendable mockOutput = new MockAppendable();
    testView = new ChessBoardTextView(testModel, mockOutput);
    assertThrows(IOException.class, () -> testView.renderMessage("test"));
  }

  @Test
  public void boardIOException() {
    testModel = new ChessBoardBuilder().build();
    Appendable mockOutput = new MockAppendable();
    testView = new ChessBoardTextView(testModel, mockOutput);
    assertThrows(IOException.class, () -> testView.renderBoard());
  }
}
