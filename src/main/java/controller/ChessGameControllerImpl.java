package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Board;
import view.text.TextView;

/**
 * Controller runs the game loop
 * and accepts user input to play
 * the chess game.
 *
 * @author Matt Stetter
 */
public class ChessGameControllerImpl implements ChessGameController {
  private Board gameBoard;
  private TextView view;
  private Readable in;

  /**
   * Simple constructor sets the passed
   * parameters to the fields.
   * @param gameBoard Board instance of a game board
   * @param view View to output to
   * @param in Readable to take input from
   * @throws IllegalArgumentException if any parameters are null
   */
  public ChessGameControllerImpl(Board gameBoard, TextView view, Readable in)
        throws IllegalArgumentException {
    if (gameBoard == null || view == null || in == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.gameBoard = gameBoard;
    this.view = view;
    this.in = in;
  }

  @Override
  public void play() {
    try {
      this.printWelcomeMessage();
    } catch (IOException ignored) {}
    Scanner userIn = new Scanner(this.in);

  }

  // Prints out the welcome message
  // to the user.
  private void printWelcomeMessage() throws IOException {
    String output = """
            Welcome to Chess
            By: Matt Stetter
            A move is made by entering: move
            followed by the starting position
            (A1-H8) and then the ending position
            using the same scheme.
            Enter "start" to start the game
            or "help" to get a list of commands.
            """;
    try {
      this.view.renderMessage(output);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
