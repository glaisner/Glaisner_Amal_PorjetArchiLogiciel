package piece;

import java.awt.Color;

public class PieceL extends AbstractPiece{

    public PieceL(Color color) {
        super(color);
        this.dimension = 3;
        this.tiles = new boolean[][] {
		{
			false,	false,	true,
			true,	true,	true,
			false,	false,	false,
		},
		{
			false,	true,	false,
			false,	true,	false,
			false,	true,	true,
		},
		{
			false,	false,	false,
			true,	true,	true,
			true,	false,	false,
		},
		{
			true,	true,	false,
			false,	true,	false,
			false,	true,	false,
		}};
        this.cols = 3;
        this.rows = 2;

        this.spawnCol = 5 - (this.dimension >> 1);
        this.spawnRow = getTopInset(0);
    }
    @Override
    public String toString(){
        return "TypeL";
    }
    
}
