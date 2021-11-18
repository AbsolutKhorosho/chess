package view.gui;

import java.awt.*;

import javax.swing.*;

import model.Board;
import model.BoardState;

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

  public ChessBoardGUIView(BoardState board) {
    super("Chess Board");

    this.setLayout(new BorderLayout());

    this.board = board;
    this.controller = new ChessFeaturesController((Board) board, this);
    this.chessBoard = new ChessBoardPanel(board);
    this.chessBoard.setController(this.controller);
    this.messageLabel = new JLabel();
    this.add((JPanel) this.chessBoard, BorderLayout.CENTER);
    JPanel textPane = new JPanel();
    textPane.setLayout(new GridLayout(0, 1));
    textPane.add(this.messageLabel, BorderLayout.PAGE_END);
    this.add(textPane);
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  @Override
  public void setBoard(Board board) {
    this.board = board;
  }
}
