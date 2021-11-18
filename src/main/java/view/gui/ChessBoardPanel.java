package view.gui;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import model.BoardState;
import model.pieces.Piece.PieceType;

public class ChessBoardPanel extends JPanel implements BoardPanel {
  private BoardState board;
  private Map<PieceType, Image> imageMap;
  private FeaturesController controller;

  private static final int cellSize = 50;

  public ChessBoardPanel(BoardState board) {
    super();
    this.board = board;
    this.setBackground(Color.WHITE);
    // TODO: Upload images here to map
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    String message = "Chess game coming soon :)";
    char[] data = new char[message.length()];
    for (int i = 0; i < message.length(); i++)
      data[i] = message.charAt(i);
    g.drawChars(data, 0, message.length(), 0, 0);
    this.setPreferredSize(new Dimension(500, 500));
  }

  @Override
  public void setController(FeaturesController controller) {
    this.controller = controller;
  }

  public void setBoard(BoardState board) {
    this.board = board;
  }
}
