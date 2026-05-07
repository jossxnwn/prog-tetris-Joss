package TetrisMain.Pieces;
import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceS extends Piece {
    public PieceS() {
        super();
        this.color = Setting.getColorPieceS();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
    }
}