
package piece;

import java.awt.Color;


public class PieceZ extends AbstractPiece{

    public PieceZ(Color color) {
        super(color);
        this.dimension = 3;
        this.tiles = new boolean[][] {
		{
			true,	true,	false,
			false,	true,	true,
			false,	false,	false,
		},
		{
			false,	false,	true,
			false,	true,	true,
			false,	true,	false,
		},
		{
			false,	false,	false,
			true,	true,	false,
			false,	true,	true,
		},
		{
			false,	true,	false,
			true,	true,	false,
			true,	false,	false,
		}};
        this.cols = 3;
        this.rows = 2;

        this.spawnCol = 5 - (this.dimension >> 1);
        this.spawnRow = getTopInset(0);
    }
    @Override
    public String toString(){
        return "TypeZ";
    }
    
    
}
