package TetrisMain.model.Pieces;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseO extends PiezaBase {
    public PiezaBaseO() {
        super();
        this.color = Setting.getColorPieceO();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
    }
    @Override
    public void rotate() {
        // La pieza O no rota
    }
}