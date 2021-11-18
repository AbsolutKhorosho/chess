import java.io.InputStreamReader;

import controller.ChessGameController;
import controller.ChessGameControllerImpl;
import model.Board;
import model.BoardState.Player;
import model.ChessBoard;
import model.ChessBoard.ChessBoardBuilder;
import view.gui.BoardGUIView;
import view.gui.ChessBoardGUIView;
import view.text.ChessBoardTextView;
import view.text.TextView;

/**
 * The main application class
 * that runs the chess game.
 *
 * @author Matt Stetter
 */
public class Chess {

  // program currently does not support
  // command-line arguments.
  public static void main(String[] args) {
    boolean gui = true;
    Player startingPlayer = Player.ONE;
    try {
      for (int i = 0; i < args.length; i++) {
        switch (args[i]) {
          case "nogui" -> gui = false;
          case "-p" -> startingPlayer = args[i+1].equals("2") ? Player.TWO : Player.ONE;
          default -> {}
        }
      }
    } catch (IndexOutOfBoundsException e) {
      System.err.println("Insufficient arguments supplied\n");
      return;
    } catch (NumberFormatException e) {
      System.err.println("Expected number but found other character\n");
      return;
    }
    Board model = new ChessBoardBuilder().player(startingPlayer).build();
    if (!gui) {
      TextView view = new ChessBoardTextView(model, System.out);
      Readable in = new InputStreamReader(System.in);
      ChessGameController controller = new ChessGameControllerImpl(view, in);
      controller.play();
    } else {
      BoardGUIView view = new ChessBoardGUIView(model);
    }
  }
}
