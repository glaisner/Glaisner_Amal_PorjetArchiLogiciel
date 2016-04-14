
package piece;

import java.awt.Color;

import panel.BoardPanel;

public class PieceFactory {
    public Piece CreatePiece(BoardPanel board, int tipo) throws Exception{
        switch(tipo){
            case 0 : return new PieceI(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX)); 
            case 1 : return new PieceJ(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX)); 
            case 2 : return new PieceL(new Color(BoardPanel.COLOR_MAX, 127, BoardPanel.COLOR_MIN));
            case 3 : return new PieceO(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN)); 
            case 4 : return new PieceS(new Color(BoardPanel.COLOR_MIN, BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN)); 
            case 5 : return new PieceT(new Color(128, BoardPanel.COLOR_MIN, 128)); 
            case 6 : return new PieceZ(new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MIN, BoardPanel.COLOR_MIN));   
            default: throw new Exception();    
        }
    }
}
