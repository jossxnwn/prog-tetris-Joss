package TetrisMain.model.Pieces;
import TetrisMain.model.*;
/**
 * Author: Josue Francis Sayritupac Izquierdo
 * Email: a25josuesi@iesantonlosada.gal
 * EmailPersonal: Josue108125@gmail.com
 * Date: 15/5/26
 */
/*indice*/

public class PiezaEspecial extends PiezaBase{
    public PiezaEspecial() throws MatrizInvalidaException{
        super();
    }

    @Override
    public int getMultiplicadorPuntuacion(){
        return 3;
    }
}
