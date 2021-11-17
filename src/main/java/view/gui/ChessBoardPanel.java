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

  }

  @Override
  public void setController(FeaturesController controller) {
    this.controller = controller;
  }

  public void setBoard(BoardState board) {
    this.board = board;
  }
}
