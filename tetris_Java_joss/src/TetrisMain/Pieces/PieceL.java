package TetrisMain.Pieces;
import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceL extends Piece {
    public PieceL() {
        super();
        this.color = Setting.getColorPieceL();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
    }
}