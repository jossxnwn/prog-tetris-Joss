package TetrisMain.model.Pieces;
import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

public class PiezaBaseS extends PiezaBase {
    public PiezaBaseS()throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPieceS();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 1, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        validarForma();
    }
}