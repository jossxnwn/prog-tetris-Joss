package TetrisMain.Pieces;


import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceI extends Piece {
    public PieceI() {
        super();
        this.color = Setting.getColorPieceI();
        this.shape = new int[][] {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
    }
}