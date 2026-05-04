package TetrisMain.Pieces;


import TetrisMain.Piece;
import TetrisMain.Setting;

public class PieceMystery extends Piece {
    public PieceMystery() {
        super();
        this.color = Setting.getColorPiecePiecemystery(); // Color estilo retro
        // Matriz 3x3 para la forma de diagonal "/"
        this.shape = new int[][] {
            {0, 0, 1},
            {0, 1, 0},
            {1, 0, 0}
        };
    }
}