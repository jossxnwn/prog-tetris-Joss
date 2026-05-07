package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

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