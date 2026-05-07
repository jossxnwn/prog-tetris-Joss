package TetrisMain.Pieces;
import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceT extends Piece {
    public PieceT() {
        super();
        this.color = Setting.getColorPieceT();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
    }
}