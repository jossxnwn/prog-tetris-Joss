package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

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