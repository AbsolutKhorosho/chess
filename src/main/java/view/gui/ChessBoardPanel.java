package view.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.PiecePosition;
import model.pieces.Piece;
import model.pieces.Piece.PieceType;

/**
 * Class that extends JPanel acts as
 * the main panel for playing the chess
 * game.
 *
 * @author Matt Stetter
 */
public class ChessBoardPanel extends JPanel implements BoardPanel {
  private BoardState board;
  private Map<PlayerPiece, Image> imageMap;
  private FeaturesController controller;

  private int originX, originY;
  private static final int cellSize = 80;
  private static final int padding = 0;

  /**
   * Constructor holds the board
   * to draw the current state of
   * the game.
   * @param board game state
   */
  public ChessBoardPanel(BoardState board) {
    super();
    this.board = board;
    setLayout(new GridLayout(8, 8));
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(8 * cellSize + padding, 8 * cellSize + padding));

    setVisible(true);
    imageMap = new HashMap<>();
    setImages();

    this.addMouseListener(new ClickMove());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (padding);
    originY = (padding);

    Piece cur;
    Image draw;

    for (int row = 0; row < board.getBoardHeight(); row++) {
      for (int col = 0; col < board.getBoardWidth(); col++) {
        cur = board.getPieceAt(new ChessPiecePosition(row, col));
        if (cur == null) {
          draw = imageMap.get(null);
        } else {
          draw = imageMap.get(new PlayerPiece(cur.getPlayer(), cur.getType()));
        }
        g.drawImage(draw,
                originX + (cellSize * col),
                originY + (cellSize * row),
                new JPanel()
        );
        g.drawRect(originX + (cellSize * col),
                originY + (cellSize * row),
                cellSize, cellSize
        );
      }
    }

  }

  private void setImages() {
    try {

      // LOAD BLACK PIECES

      Image king = ImageIO.read(new File(getAbsoluteFilePath("res\\BKing.png")));
      king = king.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.KING), king);

      Image queen = ImageIO.read(new File(getAbsoluteFilePath("res\\BQueen.png")));
      queen = queen.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.QUEEN), queen);

      Image bishop = ImageIO.read(new File(getAbsoluteFilePath("res\\BBishop.png")));
      bishop = bishop.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.BISHOP), bishop);

      Image knight = ImageIO.read(new File(getAbsoluteFilePath("res\\BKnight.png")));
      knight = knight.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.KNIGHT), knight);

      Image pawn = ImageIO.read(new File(getAbsoluteFilePath("res\\BPawn.png")));
      pawn = pawn.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.PAWN), pawn);

      Image rook = ImageIO.read(new File(getAbsoluteFilePath("res\\BRook.png")));
      rook = rook.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.ROOK), rook);

      // LOAD WHITE PIECES

      Image wKing = ImageIO.read(new File(getAbsoluteFilePath("res\\WKing.png")));
      wKing = wKing.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.KING), wKing);

      Image wQueen = ImageIO.read(new File(getAbsoluteFilePath("res\\WQueen.png")));
      wQueen = wQueen.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.QUEEN), wQueen);

      Image wBishop = ImageIO.read(new File(getAbsoluteFilePath("res\\WBishop.png")));
      wBishop = wBishop.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.BISHOP), wBishop);

      Image wKnight = ImageIO.read(new File(getAbsoluteFilePath("res\\WKnight.png")));
      wKnight = wKnight.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.KNIGHT), wKnight);

      Image wPawn = ImageIO.read(new File(getAbsoluteFilePath("res\\WPawn.png")));
      wPawn = wPawn.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.PAWN), wPawn);

      Image wRook = ImageIO.read(new File(getAbsoluteFilePath("res\\WRook.png")));
      wRook = wRook.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(new PlayerPiece(Player.TWO, PieceType.ROOK), wRook);

      // USED FOR BLANK TILE

      Image blank = ImageIO.read(new File(getAbsoluteFilePath("res\\Blank.png")));
      blank = blank.getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT);
      imageMap.put(null, blank);
    } catch (IOException e) {
      System.err.println("Could not load images");
    }
  }

  private String getAbsoluteFilePath(String path) {
    if (path.startsWith("/")) { // unix root directory
      return path;
    }
    else if (path.substring(0, 2).matches("[A-Z]:")) { // windows drives
      return path;
    }
    else {
      return ".\\" + path; // treat it as a relative path from the current directory
    }
  }

  @Override
  public void setController(FeaturesController controller) {
    this.controller = controller;
  }

  @Override
  public void highlight(PiecePosition pos) {
    Graphics g = this.getGraphics();
    g.setColor(Color.RED);
    g.fillRect(originX + (pos.getColumn() * cellSize),
            originY + (pos.getRow() * cellSize),
            cellSize, cellSize
    );
    g.setColor(Color.BLACK);
  }

  private static class PlayerPiece {
    private Player p;
    private PieceType type;

    private PlayerPiece(Player p, PieceType type) {
      this.p = p;
      this.type = type;
    }

    @Override
    public boolean equals(Object other) {
      if (other == null)
        return false;
      else if (!(other instanceof PlayerPiece))
        return false;
      else
        return this.p == ((PlayerPiece) other).p
              && this.type == ((PlayerPiece) other).type;
    }

    @Override
    public int hashCode() {
      return (this.p + "" + this.type).hashCode();
    }
  }

  private class ClickMove extends MouseAdapter {

    // Calls the processMove method in the controller
    // when a click is made on the screen.
    @Override
    public void mouseClicked(MouseEvent e) {
      super.mouseClicked(e);
      int row = (e.getY() - padding) / cellSize;
      int col = (e.getX() - padding) / cellSize;
      controller.processMove(new ChessPiecePosition(row, col));
    }
  }
}
