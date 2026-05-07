package TetrisMain.model;

import TetrisMain.model.Pieces.*;

import java.util.Random;

public class PieceFactory {
    private static final Random rand = new Random();

    public static Piece getRandomPiece() {
        // Probabilidad de 0.001% (1 de 100,000) para las secretas
        int chanceSecret = rand.nextInt(100000);

        if (chanceSecret == 0) return new PieceMystery();
        if (chanceSecret == 1) return new PiecePint();

        // 7 Piezas Clásicas si no salta la secreta
        int pieceType = rand.nextInt(7);
        switch(pieceType) {
            case 0: return new PieceI();
            case 1: return new PieceJ();
            case 2: return new PieceL();
            case 3: return new PieceO();
            case 4: return new PieceS();
            case 5: return new PieceT();
            case 6: return new PieceZ();
            default: return new PieceL();
        }
    }
}