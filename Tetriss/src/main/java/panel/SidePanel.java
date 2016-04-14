package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Tetris;

import piece.Piece;

public class SidePanel extends JPanel {

	private static final long serialVersionUID = 2181495598854992747L;
	private static final int TILE_SIZE = BoardPanel.TILE_SIZE >> 1;
	private static final int INSET = 20;
	private static final int TEXT_STRIDE = 25;
	private static final Color TEXT_COLOR = new Color(255,255,255);
	private Tetris tetris;

	public SidePanel(Tetris tetris) {
		this.tetris = tetris;
		setPreferredSize(new Dimension(125, BoardPanel.PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(TEXT_COLOR);
		g.drawString("Score: " + tetris.getScore(), INSET, TEXT_STRIDE);		
	}
	

	@SuppressWarnings("unused")
	private void drawTile(Piece type, int x, int y, Graphics g) {
		g.setColor(type.getBaseColor());
		g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

	}
	
}
