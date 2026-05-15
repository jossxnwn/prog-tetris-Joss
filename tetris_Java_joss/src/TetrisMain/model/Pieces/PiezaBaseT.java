package TetrisMain.model.Pieces;
import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseT extends PiezaBase {
    public PiezaBaseT() throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPieceT();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        validarForma();
    }
}