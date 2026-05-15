package TetrisMain.model.Pieces;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseZ extends PiezaBase {
    public PiezaBaseZ() {
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