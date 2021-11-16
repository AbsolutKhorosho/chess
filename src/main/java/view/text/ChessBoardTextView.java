package view.text;

import java.io.IOException;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.pieces.Piece;

public class ChessBoardTextView implements TextView {
  private BoardState board;
  private final Appendable destination;

  public ChessBoardTextView(BoardState board, Appendable destination) {
    this.board = board;
    this.destination = destination;
  }

  // Renders the message to the
  // specified appendable.
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      destination.append(message);
    } catch (IOException e) {
      throw new IOException("Could not render the message");
    }
  }

  // Renders the current state
  // of the board.
  @Override
  public void renderBoard() throws IOException {
    try {
      this.destination.append(this.boardOutput());
    } catch (IOException e) {
      throw new IOException("Could not render the board");
    }
  }

  private String boardOutput() {
    StringBuilder output = new StringBuilder();
    for (int row = 0; row < this.board.getBoardHeight(); row++) {
      for (int col = 0; col < this.board.getBoardWidth(); col++) {
        Piece curPiece = this.board.getPieceAt(new ChessPiecePosition(row, col));
        if (curPiece == null) {
          output.append('o').append(' ');
          continue;
        }
        if (row == 1 && col == 0) {
          System.out.println(curPiece);
        }
        char c = switch (curPiece.getType()) {
          case PAWN -> curPiece.getPlayer() == Player.ONE ? 'P' : 'p';
          case ROOK -> curPiece.getPlayer() == Player.ONE ? 'R' : 'r';
          case KNIGHT -> curPiece.getPlayer() == Player.ONE ? 'H' : 'h';
          case BISHOP -> curPiece.getPlayer() == Player.ONE ? 'B' : 'b';
          case QUEEN -> curPiece.getPlayer() == Player.ONE ? 'Q' : 'q';
          default -> curPiece.getPlayer() == Player.ONE ? 'K' : 'k';
        };
        output.append(c).append(' ');
      }
      output.append("\n");
    }
    return output.toString();
  }
}
