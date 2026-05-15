package TetrisMain.model.Pieces;
import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseMystery extends PiezaBase {
    public PiezaBaseMystery() throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPiecePiecemystery();
        this.shape = new int[][] {
                {0, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {1, 0, 0, 0}
        };
        validarForma();
    }
}