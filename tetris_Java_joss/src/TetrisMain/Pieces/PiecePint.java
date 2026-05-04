package TetrisMain.Pieces;

// Ya no necesitas importar java.awt.Color aquí si no lo usas directamente,
// porque Setting te devuelve el objeto Color.

import TetrisMain.Piece;
import TetrisMain.Setting;

public class PiecePint extends Piece {

    public PiecePint() {
        super();

        // Asignamos el color leyendo la configuración global
        this.color = Setting.getColorPiecePint();

        this.shape = new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
    }
}