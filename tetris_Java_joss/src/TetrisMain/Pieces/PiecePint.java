package TetrisMain.Pieces;
import TetrisMain.Piece;
import TetrisMain.Setting;

public class PiecePint extends Piece {
    public PiecePint() {
        super();
        this.color = Setting.getColorPiecePint();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
    }
}