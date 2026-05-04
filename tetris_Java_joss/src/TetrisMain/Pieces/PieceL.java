package TetrisMain.Pieces;


import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceL extends Piece {
    public PieceL() {
        super();
        this.color = Setting.getColorPieceL();
        // Matriz 3x3 para la pieza L
        this.shape = new int[][] {
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
    }
}