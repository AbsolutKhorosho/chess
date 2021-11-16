import java.io.InputStreamReader;

import controller.ChessGameController;
import controller.ChessGameControllerImpl;
import model.Board;
import model.BoardState.Player;
import model.ChessBoard;
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
    Board model = new ChessBoard(Player.ONE);
    TextView view = new ChessBoardTextView(model, System.out);
    Readable in = new InputStreamReader(System.in);
    ChessGameController controller = new ChessGameControllerImpl(model, view, in);
    controller.play();
  }
}
