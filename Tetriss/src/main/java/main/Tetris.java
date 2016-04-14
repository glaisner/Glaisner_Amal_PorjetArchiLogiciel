package main;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import panel.BoardPanel;
import panel.SidePanel;
import piece.Piece;
import piece.PieceFactory;

public class Tetris extends JFrame {

	private static final long serialVersionUID = -4722429764792514382L;
	private static final long FRAME_TIME = 1000L / 50L;
	private static final int TYPE_COUNT = 7;
	private BoardPanel board;
	private SidePanel side;
	private boolean isPaused;
	private boolean isNewGame;
	private boolean isGameOver;
	private int level;
	private int score;
	private Random random;
	private Clock logicTimer;
	private Piece currentType;
	private Piece nextType;
	private int currentCol;
	private int currentRow;
	private int currentRotation;
	private int dropCooldown;
	private float gameSpeed;
    private PieceFactory pieceFactory;
	private Tetris() {

		super("Glaisner-Amal:TetrisDuplicate");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		this.board = new BoardPanel(this);
		this.side = new SidePanel(this);

		add(board, BorderLayout.CENTER);
		add(side, BorderLayout.EAST);

		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
								
				switch(e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					if(!isPaused && dropCooldown == 0) {
						logicTimer.setCyclesPerSecond(25.0f);
					}
					break;
				case KeyEvent.VK_LEFT:
					if(!isPaused && board.isValidAndEmpty(currentType, currentCol - 1, currentRow, currentRotation)) {
						currentCol--;
					}
					break;
				case KeyEvent.VK_RIGHT:
					if(!isPaused && board.isValidAndEmpty(currentType, currentCol + 1, currentRow, currentRotation)) {
						currentCol++;
					}
					break;
				case KeyEvent.VK_UP:
					if(!isPaused) {
						rotatePiece((currentRotation == 0) ? 3 : currentRotation - 1);
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!isPaused) {
						rotatePiece((currentRotation == 3) ? 0 : currentRotation + 1);
					}
					break;
				case KeyEvent.VK_P:
					if(!isGameOver && !isNewGame) {
						isPaused = !isPaused;
						logicTimer.setPaused(isPaused);
					}
					break;

				case KeyEvent.VK_ENTER:
					if(isGameOver || isNewGame) {
                                    try {
                                        resetGame();
                                    } catch (Exception ex) {
                                        Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
                                    }
					}
					break;
				
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				switch(e.getKeyCode()) {

				case KeyEvent.VK_SPACE:
					logicTimer.setCyclesPerSecond(gameSpeed);
					logicTimer.reset();
					break;
				}
				
			}
			
		});

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void startGame() throws Exception {

		this.random = new Random();
		this.isNewGame = true;
		this.gameSpeed = 1.0f;
		this.logicTimer = new Clock(gameSpeed);
		logicTimer.setPaused(true);
		
		while(true) {

			long start = System.nanoTime();

			logicTimer.update();

			if(logicTimer.hasElapsedCycle()) {
				updateGame();
			}

			if(dropCooldown > 0) {
				dropCooldown--;
			}

			renderGame();

			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < FRAME_TIME) {
				try {
					Thread.sleep(FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void updateGame() throws Exception {

		if(board.isValidAndEmpty(currentType, currentCol, currentRow + 1, currentRotation)) {
			currentRow++;
		} else {
			board.addPiece(currentType, currentCol, currentRow, currentRotation);
			int cleared = board.checkLines();
			if(cleared > 0) {
				score += 50 << cleared;
			}
			dropCooldown = 25;

			spawnPiece();
		}		
	}

	private void renderGame() {
		board.repaint();
		side.repaint();
	}
	

	private void resetGame() throws Exception{
        this.pieceFactory = new PieceFactory();
		this.level = 1;
		this.score = 0;
		this.gameSpeed = 1.0f;
		//this.nextType = Tile.values()[random.nextInt(TYPE_COUNT)];
                this.nextType = this.pieceFactory.CreatePiece(this.board,random.nextInt(TYPE_COUNT));
		this.isNewGame = false;
		this.isGameOver = false;		
		board.clear();
		logicTimer.reset();
		logicTimer.setCyclesPerSecond(gameSpeed);
		spawnPiece();
	}
	
	
	private void spawnPiece() throws Exception{
		this.currentType = nextType;
		this.currentCol = currentType.getSpawnColumn();
		this.currentRow = currentType.getSpawnRow();
		this.currentRotation = 0;
		this.nextType = this.pieceFactory.CreatePiece(this.board,random.nextInt(TYPE_COUNT));
		if(!board.isValidAndEmpty(currentType, currentCol, currentRow, currentRotation)) {
			this.isGameOver = true;
			logicTimer.setPaused(true);
		}		
	}

	private void rotatePiece(int newRotation) {
		int newColumn = currentCol;
		int newRow = currentRow;
		int left = currentType.getLeftInset(newRotation);
		int right = currentType.getRightInset(newRotation);
		int top = currentType.getTopInset(newRotation);
		int bottom = currentType.getBottomInset(newRotation);
		if(currentCol < -left) {
			newColumn -= currentCol - left;
		} else if(currentCol + currentType.getDimension() - right >= BoardPanel.COL_COUNT) {
			newColumn -= (currentCol + currentType.getDimension() - right) - BoardPanel.COL_COUNT + 1;
		}
		if(currentRow < -top) {
			newRow -= currentRow - top;
		} else if(currentRow + currentType.getDimension() - bottom >= BoardPanel.ROW_COUNT) {
			newRow -= (currentRow + currentType.getDimension() - bottom) - BoardPanel.ROW_COUNT + 1;
		}
		if(board.isValidAndEmpty(currentType, newColumn, newRow, newRotation)) {
			currentRotation = newRotation;
			currentRow = newRow;
			currentCol = newColumn;
		}
	}
	
	
	public boolean isPaused() {
		return isPaused;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isNewGame() {
		return isNewGame;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public Piece getPieceType() {
		return currentType;
	}

	public Piece getNextPieceType() {
		return nextType;
	}

	public int getPieceCol() {
		return currentCol;
	}
	
	public int getPieceRow() {
		return currentRow;
	}
	
	public int getPieceRotation() {
		return currentRotation;
	}
	
	public static void main(String[] args) throws Exception{
            Tetris tetris = new Tetris();
            tetris.startGame();
         
           
	}

}
