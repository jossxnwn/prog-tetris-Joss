package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

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