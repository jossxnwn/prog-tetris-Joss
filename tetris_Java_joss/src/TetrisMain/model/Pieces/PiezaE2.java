package TetrisMain.model.Pieces;

import TetrisMain.model.MatrizInvalidaException;
import TetrisMain.model.PiezaBase;
import TetrisMain.model.Setting;

/**
 * Author: Josue Francis Sayritupac Izquierdo
 * Email: a25josuesi@iesantonlosada.gal
 * EmailPersonal: Josue108125@gmail.com
 * Date: 15/5/26
 */
/*indice*/

public class PiezaE2 extends PiezaBase {
    public PiezaE2() throws MatrizInvalidaException {
        super();
        this.color = Setting.getColorPieceE2();
        this.shape = new int[][] {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        validarForma();
    }
}