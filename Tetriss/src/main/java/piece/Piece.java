package piece;

import java.awt.Color;

public interface Piece {
    public Color getBaseColor();
    public Color getLightColor();
    public Color getDarkColor();
    public int getDimension();
    public int getSpawnColumn();
    public int getSpawnRow();
    public int getRows();
    public int getCols();
    public boolean isPiece(int x, int y, int rotation);
    public int getLeftInset(int rotation);
    public int getRightInset(int rotation);
    public int getTopInset(int rotation);
    public int getBottomInset(int rotation);
}
