package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Tetris;

import piece.Piece;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 5055679736784226108L;
	public static final int COLOR_MIN = 35;
	public static final int COLOR_MAX = 255 - COLOR_MIN;
	private static final int BORDER_WIDTH = 5;
	public static final int COL_COUNT = 10;
	private static final int VISIBLE_ROW_COUNT = 20;
	private static final int HIDDEN_ROW_COUNT = 2;
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	public static final int TILE_SIZE = 24;
	public static final int SHADE_WIDTH = 4;
	private static final int CENTER_X = COL_COUNT * TILE_SIZE / 2;
	private static final int CENTER_Y = VISIBLE_ROW_COUNT * TILE_SIZE / 2;
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	public static final int PANEL_HEIGHT = VISIBLE_ROW_COUNT * TILE_SIZE + BORDER_WIDTH * 2;
	private Tetris tetris;
	private Piece[][] piece;
	public BoardPanel(Tetris tetris) {
		this.tetris = tetris;
		this.piece = new Piece[ROW_COUNT][COL_COUNT];
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}

	public void clear() {

		for(int i = 0; i < ROW_COUNT; i++) {
			for(int j = 0; j < COL_COUNT; j++) {
				piece[i][j] = null;
			}
		}
	}

	public boolean isValidAndEmpty( Piece type, int x, int y, int rotation) {
		if(x < -type.getLeftInset(rotation) || x + type.getDimension() - type.getRightInset(rotation) >= COL_COUNT) {
			return false;
		}
		if(y < -type.getTopInset(rotation) || y + type.getDimension() - type.getBottomInset(rotation) >= ROW_COUNT) {
			return false;
		}
		for(int col = 0; col < type.getDimension(); col++) {
			for(int row = 0; row < type.getDimension(); row++) {
				if(type.isPiece(col, row, rotation) && isOccupied(x + col, y + row)) {
					return false;
				}
			}
		}
		return true;
	}
	public void addPiece(Piece type, int x, int y, int rotation) {
		for(int col = 0; col < type.getDimension(); col++) {
			for(int row = 0; row < type.getDimension(); row++) {
				if(type.isPiece(col, row, rotation)) {
					setPiece(col + x, row + y, type);
				}
			}
		}
	}
	public int checkLines() {
		int completedLines = 0;
		for(int row = 0; row < ROW_COUNT; row++) {
			if(checkLine(row)) {
				completedLines++;
			}
		}
		return completedLines;
	}

	private boolean checkLine(int line) {

		for(int col = 0; col < COL_COUNT; col++) {
			if(!isOccupied(col, line)) {
				return false;
			}
		}
		for(int row = line - 1; row >= 0; row--) {
			for(int col = 0; col < COL_COUNT; col++) {
				setPiece(col, row + 1, getPiece(col, row));
			}
		}
		return true;
	}
	
	

	private boolean isOccupied(int x, int y) {
		return piece[y][x] != null;
	}
	

	private void setPiece(int  x, int y, Piece type) {
		piece[y][x] = type;
	}

	private Piece getPiece(int x, int y) {
		return piece[y][x];
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.translate(BORDER_WIDTH, BORDER_WIDTH);

		if(tetris.isPaused()) {

			g.setColor(Color.WHITE);
			String msg = "PAUSED";
			g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, CENTER_Y);
		} else if(tetris.isNewGame() || tetris.isGameOver()) {
			g.setColor(Color.WHITE);
			String msg = tetris.isNewGame() ? "TETRIS" : "GAME OVER";
			g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 150);
			msg = "Press Enter to Play" + (tetris.isNewGame() ? "" : " Again");
			g.drawString(msg, CENTER_X - g.getFontMetrics().stringWidth(msg) / 2, 300);
		} else {for(int x = 0; x < COL_COUNT; x++) {
				for(int y = HIDDEN_ROW_COUNT; y < ROW_COUNT; y++) {
					Piece piece = getPiece(x, y);
					if(piece != null) {
						drawPiece(piece, x * TILE_SIZE, (y - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
					}
				}
			}
			//dessin de la piece sur la grille
			Piece type = tetris.getPieceType();
			int pieceCol = tetris.getPieceCol();
			int pieceRow = tetris.getPieceRow();
			int rotation = tetris.getPieceRotation();
			for(int col = 0; col < type.getDimension(); col++) {
				for(int row = 0; row < type.getDimension(); row++) {
					if(pieceRow + row >= 2 && type.isPiece(col, row, rotation)) {
						drawPiece(type, (pieceCol + col) * TILE_SIZE, (pieceRow + row - HIDDEN_ROW_COUNT) * TILE_SIZE, g);
					}
				}
			}
			g.setColor(Color.DARK_GRAY);
			for(int x = 0; x < COL_COUNT; x++) {
				for(int y = 0; y < VISIBLE_ROW_COUNT; y++) {
					g.drawLine(0, y * TILE_SIZE, COL_COUNT * TILE_SIZE, y * TILE_SIZE);
					g.drawLine(x * TILE_SIZE, 0, x * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
				}
			}
		}

		g.setColor(Color.WHITE);
		g.drawRect(0, 0, TILE_SIZE * COL_COUNT, TILE_SIZE * VISIBLE_ROW_COUNT);
	}

	private void drawPiece(Piece type, int x, int y, Graphics g) {
		drawPiece(type.getBaseColor(), type.getLightColor(), type.getDarkColor(), x, y, g);
	}
	//Dessin des pieces
	private void drawPiece(Color base, Color light, Color dark, int x, int y, Graphics g) {

		g.setColor(base);
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

	}

}
