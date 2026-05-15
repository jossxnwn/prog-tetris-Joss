package TetrisMain.model.Pieces;
import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseL extends PiezaBase {
    public PiezaBaseL() throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPieceL();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        validarForma();
    }
}