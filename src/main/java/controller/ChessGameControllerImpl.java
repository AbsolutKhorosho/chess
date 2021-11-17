package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Board;
import model.Board.State;
import model.BoardBuilder;
import model.BoardState.Player;
import model.ChessBoard;
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
   * @param view View to output to
   * @param in Readable to take input from
   * @throws IllegalArgumentException if any parameters are null
   */
  public ChessGameControllerImpl(TextView view, Readable in)
        throws IllegalArgumentException {
    if (view == null || in == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
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
    while (!entered.startsWith("quit")) {
      String[] args = entered.split(" ");
      if (args.length < 1) {
        entered = userIn.nextLine();
        continue;
      }
      switch (args[0]) {
        case "start" -> {
          this.gameBoard = reset(args);
          this.view.updateBoard(gameBoard);
          gameLoop(userIn);
          try {
            this.printGameOverMessage();
          } catch (IOException ignored) {}
        }
        default -> {}
      }
      entered = userIn.nextLine();
    }
    try {
      this.printQuitMessage();
    } catch (IOException ignored) {}
    userIn.close();
  }

  // Run by the play method
  // when a new game is started.
  private void gameLoop(Scanner userIn) {
    String[] moves = new String[2];
    while (this.gameBoard.isGameOver() == State.IN_PROGRESS) {
      try {
        this.view.renderBoard();
        this.view.renderMessage(this.gameBoard.getCurrentPlayer().toString()
                + " enter a move: ");
      } catch (IOException ignored) {}
      moves[0] = userIn.next();
      moves[1] = userIn.next();
      if (moves[0].equalsIgnoreCase("quit") || moves[0].equalsIgnoreCase("q")) {
        break;
      }
      PiecePosition start = getPosition(moves[0]);
      PiecePosition end = getPosition(moves[1]);
      try {
        this.gameBoard.move(start, end);
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage(e.getMessage() + "\n");
        } catch (IOException j) {
          System.err.println(j.getMessage());
        }
      }
    }
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      System.err.println(e.getMessage() + System.lineSeparator());
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
            A move is made by entering the starting
            position (A1-H8) and then the ending position
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
      System.err.println(e.getMessage() + System.lineSeparator());
    }
  }

  // Prints out the message after the
  // game is over.
  private void printGameOverMessage() throws IOException {
    String output = """
            GAME OVER!
            ---------------------------------
            Type start to play again
            """;
    try {
      this.view.renderMessage(output);
    } catch (IOException e) {
      System.err.println(e.getMessage() + System.lineSeparator());
    }
  }

  // Resets the board by creating
  // a new instance.
  private Board reset(String[] userInput) throws IllegalArgumentException {
    BoardBuilder build = new ChessBoard.ChessBoardBuilder();
    try {
      for (int i = 1; i < userInput.length; i++) {
        switch (userInput[i]) {
          case "-p" -> {
            Player start = userInput[i + 1].equals("1") ? Player.ONE :
                    (userInput[i + 1].equals("2") ? Player.TWO : null);
            if (start == null) {
              continue;
            }
            build = build.player(start);
          }
          case "-w" -> {
            int width = Integer.parseInt(userInput[i + 1]);
            build = build.width(width);
          }
          case "-h" -> {
            int height = Integer.parseInt(userInput[i + 1]);
            build = build.height(height);
          }
          default -> {
          }
        }
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Insufficient arguments supplied.");
    } catch (NumberFormatException f) {
      throw new IllegalArgumentException("Non-numeric value supplied as argument.");
    }
    return build.build();
  }
}
