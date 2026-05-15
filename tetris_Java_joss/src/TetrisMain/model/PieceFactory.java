package TetrisMain.model;
import TetrisMain.model.Pieces.*;
import java.util.Random;

public class PieceFactory {
    private static final Random rand = new Random();

    public static PiezaBase getRandomPiece() {
        // Probabilidad de 0.001% (1 de 100,000) para las secretas
        try{
            int chanceSecret = rand.nextInt(100000);

            if (chanceSecret == 0) return new PiezaBaseMystery();
            if (chanceSecret == 1) return new PiezaE1();
            if (chanceSecret == 1) return new PiezaE2();

            // 7 Piezas Clásicas si no salta la secreta
            int pieceType = rand.nextInt(7);
            switch(pieceType) {
                case 0: return new PiezaBaseI();
                case 1: return new PiezaBaseJ();
                case 2: return new PiezaBaseL();
                case 3: return new PiezaBaseO();
                case 4: return new PiezaBaseS();
                case 5: return new PiezaBaseT();
                case 6: return new PiezaBaseZ();
                default: return new PiezaBaseL();

            }
        }
        catch (MatrizInvalidaException e){
            System.out.println("Estado incoherente: " + e);
            System.exit(1);
            return null;
        }
    }
}