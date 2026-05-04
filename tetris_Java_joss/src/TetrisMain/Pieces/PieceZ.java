package TetrisMain.Pieces;


import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceZ extends Piece {
    public PieceZ() {
        super();
        this.color = Setting.getColorPieceZ();
        this.shape = new int[][] {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
        };
    }
}