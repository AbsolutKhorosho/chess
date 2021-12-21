package view.gui;

import java.awt.*;

import javax.swing.*;

import model.Board;
import model.BoardState;
import model.PiecePosition;

/**
 * A simple GUI written in swing
 * for a chess game.
 *
 * @author Matt Stetter
 */
public class ChessBoardGUIView extends JFrame implements BoardGUIView {
  private BoardState board;
  private FeaturesController controller;
  private BoardPanel chessBoard;
  private JLabel messageLabel;

  /**
   * Creates the main pane to
   * display the chess game and
   * necessary text.
   * @param board chess board state
   */
  public ChessBoardGUIView(BoardState board) {
    super("Chess Board");

    setSize(new Dimension(500, 500));
    setLayout(new GridLayout(1, 2));

    JPanel chessPanel = new JPanel();
    this.board = board;
    this.controller = new ChessFeaturesController((Board) board, this);
    this.chessBoard = new ChessBoardPanel(board);
    this.chessBoard.setController(this.controller);
    chessPanel.add((JPanel)this.chessBoard);

    JPanel textPanel = new JPanel();
    this.messageLabel = new JLabel();
    this.messageLabel.setText("Hello world");
    textPanel.add(this.messageLabel);

    add(chessPanel);
    add(textPanel);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  // Refreshes the GUI window.
  @Override
  public void refresh() {
    this.repaint();
  }

  // Renders the specified string to the
  // text box on the right of the window.
  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  // Calls the GUI chess board to highlight a square
  // at the specified position.
  @Override
  public void highlightSquare(PiecePosition pos) {
    this.chessBoard.highlight(pos);
  }
}
