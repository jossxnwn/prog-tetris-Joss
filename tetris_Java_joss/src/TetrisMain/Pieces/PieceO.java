package TetrisMain.Pieces;


import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceO extends Piece {
    public PieceO() {
        super();
        this.color = Setting.getColorPieceO();
        this.shape = new int[][] {
            {1, 1},
            {1, 1}
        };
    }

    @Override
    public void rotate() {
        // La pieza O no cambia al rotar, no hacemos nada
    }
}