package view.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.pieces.Piece;
import model.pieces.Piece.PieceType;

public class ChessBoardPanel extends JPanel implements BoardPanel {
  private BoardState board;
  private Map<PlayerPiece, Image> imageMap;
  private FeaturesController controller;
  private JLabel[][] imageGrid;

  private int originX, originY;
  private static final int cellSize = 50;
  private static final int padding = 100;

  public ChessBoardPanel(BoardState board) {
    super();
    this.board = board;
    setLayout(new GridLayout(8, 8));
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(8 * cellSize + padding, 8 * cellSize + padding));
    this.imageGrid = new JLabel[8][8];
    setIconMap();

    setVisible(true);
    imageMap = new HashMap<>();
    setImages();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (padding);
    originY = (padding);

    if (this.imageMap.get(new PlayerPiece(Player.ONE, PieceType.PAWN)) == null) {
      return;
    }

    Piece cur;
    Image draw;
    JLabel drawThis = this.imageGrid[0][0];

    drawThis.setIcon(new ImageIcon(this.imageMap.get(new PlayerPiece(Player.ONE, PieceType.PAWN))));

//    for (int row = 0; row < board.getBoardHeight(); row++) {
//      for (int col = 0; col < board.getBoardWidth(); col++) {
//        cur = board.getPieceAt(new ChessPiecePosition(row, col));
//        if (cur == null) {
//          draw = imageMap.get(null);
//        } else {
//          draw = imageMap.get(new PlayerPiece(Player.ONE, cur.getType()));
//        }
//        drawGraph.drawImage(draw, originX + (col * cellSize), originY + (row * cellSize), new JPanel());
//      }
//    }
  }

  private void setImages() {
    try {
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

  private void setIconMap() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        this.imageGrid[i][j] = new JLabel();
        this.add(this.imageGrid[i][j]);
      }
    }
  }

  @Override
  public void setController(FeaturesController controller) {
    this.controller = controller;
  }

  public void setBoard(BoardState board) {
    this.board = board;
  }

  private static class PlayerPiece {
    private Player p;
    private PieceType type;

    public PlayerPiece(Player p, PieceType type) {
      this.p = p;
      this.type = type;
    }

    public boolean equals(Object other) {
      if (other == null)
        return false;
      else if (!(other instanceof PlayerPiece))
        return false;
      else
        return this.p == ((PlayerPiece) other).p
              && this.type == ((PlayerPiece) other).type;
    }
  }
}
