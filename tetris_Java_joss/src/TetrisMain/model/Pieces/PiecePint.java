package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

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