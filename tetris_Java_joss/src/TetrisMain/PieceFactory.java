package TetrisMain;

import TetrisMain.Pieces.*;

import java.util.Random;

/**
 * Esta clase me gestiona la llamada a todas las piezas
 */
public class PieceFactory {
    private static final Random rand = new Random();

    /**
     * Funcion para retornarme una pieza ramdon
     * @return
     */
    public static Piece getRandomPiece() {
        int chance = rand.nextInt(100); // 0 a 99

        // 2% de probabilidad de que salga la pieza misteriosa "/"
        if (chance < 2) {
            return new PieceMystery();
        }

        // Elegir aleatoriamente entre las 7 piezas clásicas
        int pieceType = rand.nextInt(9); // Da un número del 0 al 6

        switch(pieceType) {
            case 0: return new PieceI();
            case 1: return new PieceJ();
            case 2: return new PieceL();
            case 3: return new PieceO();
            case 4: return new PieceS();
            case 5: return new PieceT();
            case 6: return new PieceZ();
            case 7: return new PieceMystery();
            case 8: return new PiecePint();
            default: return new PieceL(); // Fallback de seguridad
        }
    }
}