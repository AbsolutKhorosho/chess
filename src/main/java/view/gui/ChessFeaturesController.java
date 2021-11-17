package view.gui;

import model.Board;
import model.PiecePosition;

/**
 * A simple controller class that
 * processes the input that the
 * GUI sends to the model.
 *
 * @author Matt Stetter
 */
public class ChessFeaturesController implements FeaturesController {
  private Board board;
  private BoardGUIView view;

  public ChessFeaturesController(Board board, BoardGUIView view) {
    this.view = view;
  }

  @Override
  public void processMove(PiecePosition pos) {

  }

}
