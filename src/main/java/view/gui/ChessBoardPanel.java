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

  private int originX, originY;
  private static final int cellSize = 50;
  private static final int padding = 100;

  public ChessBoardPanel(BoardState board) {
    super();
    this.board = board;
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(8 * cellSize + padding, 8 * cellSize + padding));

    imageMap = new HashMap<>();
    setImages();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (getPreferredSize().getWidth() / 2 - board.getBoardWidth() * cellSize / 2);
    originY = (int) (getPreferredSize().getHeight() / 2 - board.getBoardHeight() * cellSize / 2);

    Piece cur;
    Image draw;
    for (int row = 0; row < board.getBoardHeight(); row++) {
      for (int col = 0; col < board.getBoardWidth(); col++) {
        cur = board.getPieceAt(new ChessPiecePosition(row, col));
        if (cur == null) {
          draw = imageMap.get(null);
        } else {
          draw = imageMap.get(new PlayerPiece(Player.ONE, cur.getType()));
        }
        g.drawImage(draw, originX + (col * cellSize), originY + (row * cellSize), new JPanel());
      }
    }
  }

  private void setImages() {
    try {
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.KING),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BKing.png")));
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.QUEEN),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BQueen.png")));
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.BISHOP),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BBishop.png")));
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.ROOK),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BRook.png")));
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.KNIGHT),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BKnight")));
      imageMap.put(new PlayerPiece(Player.ONE, PieceType.PAWN),
              ImageIO.read(new File("D:IdeaProjects\\chess\\res\\BPawn.png")));
      imageMap.put(null, ImageIO.read(new File("D:IdeaProjects\\chess\\res\\Blank.png")));
    } catch (IOException e) {
      System.err.println("Could not load images");
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
