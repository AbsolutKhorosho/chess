package controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import model.Board;
import model.Board.State;
import model.ChessPiecePosition;
import model.PiecePosition;
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

  // Plays the game, prompting the
  // user for input and making the
  // moves until the game is over.
  @Override
  public void play() {
    try {
      this.printWelcomeMessage();
    } catch (IOException ignored) {}
    Scanner userIn = new Scanner(this.in);
    String entered = userIn.nextLine();
    while (!entered.equals("quit")) {
      switch (entered) {
        case "start":
          gameLoop();
          break;
        default:
          try {
            this.view.renderMessage("Unknown command");
          } catch (IOException ignored) {}
      }
      entered = userIn.nextLine();
    }
    try {
      this.printQuitMessage();
    } catch (IOException ignored) {}
  }

  // Run by the play method
  // when a new game is started.
  private void gameLoop() {
    Scanner userIn = new Scanner(this.in);
    String[] moves;
    while (this.gameBoard.isGameOver() == State.IN_PROGRESS) {
      try {
        this.view.renderBoard();
        this.view.renderMessage(this.gameBoard.getCurrentPlayer().toString()
                + " enter a move: ");
      } catch (IOException ignored) {}
      moves = userIn.nextLine().split(" ");
      if (moves.length != 2) {
        try {
          this.view.renderMessage("Invalid input move");
          continue;
        } catch (IOException ignored) {}
      }
      PiecePosition start = getPosition(moves[0]);
      PiecePosition end = getPosition(moves[1]);
      try {
        this.gameBoard.move(start, end);
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage(e.getMessage());
        } catch (IOException j) {
          System.err.println(j.getMessage());
        }
      }
    }
  }

  private PiecePosition getPosition(String pos) {
    pos = pos.toLowerCase();
    char colLetter = pos.charAt(0);
    int col = (int)colLetter - 97;
    int row = this.gameBoard.getBoardHeight() - Integer.parseInt(pos.charAt(1) + "");
    return new ChessPiecePosition(row, col);
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

  // Prints out the goodbye message
  // at the end of the program.
  private void printQuitMessage() throws IOException {
    String output = """
            Thanks for playing!
            """;
    try {
      this.view.renderMessage(output);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
