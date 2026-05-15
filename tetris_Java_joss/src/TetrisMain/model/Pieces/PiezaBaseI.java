package TetrisMain.model.Pieces;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseI extends PiezaBase {
    public PiezaBaseI() {
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