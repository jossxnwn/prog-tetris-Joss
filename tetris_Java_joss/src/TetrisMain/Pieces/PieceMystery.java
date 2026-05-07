package TetrisMain.Pieces;
import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceMystery extends Piece {
    public PieceMystery() {
        super();
        this.color = Setting.getColorPiecePiecemystery();
        this.shape = new int[][] {
                {0, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 0, 0}
        };
    }
}