package TetrisMain.model.Pieces;
import TetrisMain.model.Piece;
import TetrisMain.model.Setting;

public class PieceJ extends Piece {
    public PieceJ() {
        super();
        this.color = Setting.getColorPieceJ();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
    }
}