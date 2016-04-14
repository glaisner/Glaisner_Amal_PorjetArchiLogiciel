package piece;
import java.awt.Color;


public class PieceO extends AbstractPiece{

    public PieceO(Color color) {
        super(color);
        this.dimension = 2;
        this.tiles = new boolean[][] {
		{
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		},
		{	
			true,	true,
			true,	true,
		},
		{
			true,	true,
			true,	true,
		}};
        this.cols = 2;
        this.rows = 2;

        this.spawnCol = 5 - (this.dimension >> 1);
        this.spawnRow = getTopInset(0);
    }
    @Override
    public String toString(){
        return "TypeO";
    }
    
}
