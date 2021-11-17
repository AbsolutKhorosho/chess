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

  public ChessBoardGUIView(BoardState board) {
    super("Chess Board");

    this.setLayout(new BorderLayout());

    this.board = board;
    this.controller = new ChessFeaturesController((Board) board, this);
    this.chessBoard = new ChessBoardPanel(board);
  }

  @Override
  public void refresh() {

  }

  @Override
  public void renderMessage(String message) {

  }

  @Override
  public void setBoard(Board board) {
    this.board = board;
  }
}
