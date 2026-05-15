package TetrisMain.model.Pieces;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseJ extends PiezaBase {
    public PiezaBaseJ() {
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