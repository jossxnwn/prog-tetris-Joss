package TetrisMain.model.Pieces;
import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaE1 extends PiezaBase {
    public PiezaE1() throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPieceE1();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        validarForma();
    }
}