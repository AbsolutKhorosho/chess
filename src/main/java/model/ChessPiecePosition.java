package model;

/**
 * Concrete class for a position on
 * the chess board. Pass row/column
 * in algebraic format, but return row
 * and column as integer values.
 */
public class ChessPiecePosition implements PiecePosition {
  private int row, col;

  /**
   * Constructor calculates the numeric
   * values for the row and column
   * based on the algebraic notation
   * passed to it.
   * @param row row (A-H)
   * @param col column (1-8)
   */
  public ChessPiecePosition(int row, int col) {
    this.row = row;
    this.col = col;
  }

  // Returns the row value
  @Override
  public int getRow() {
    return this.row;
  }

  // Returns the column value
  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (!(other instanceof PiecePosition)) {
      return false;
    } else {
      return this.col == ((ChessPiecePosition) other).getColumn()
              && this.row == ((ChessPiecePosition) other).getRow();
    }
  }

  @Override
  public int hashCode() {
    return (this.row + " " + this.col).hashCode();
  }
}
