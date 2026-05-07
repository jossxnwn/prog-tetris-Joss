package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

public class PieceZ extends Piece {
    public PieceZ() {
        super();
        this.color = Setting.getColorPieceZ();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        };
    }
}