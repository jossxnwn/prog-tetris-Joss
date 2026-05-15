package TetrisMain.model.Pieces;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBasePint extends PiezaBase {
    public PiezaBasePint() {
        super();
        this.color = Setting.getColorPiecePint();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
    }
}