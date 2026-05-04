package TetrisMain;

import java.awt.Color;

public class Setting {

    /*Configuracion de tamaño del panel de juego
    * Configuracion para el tamaño del tablero de juego*/
    private static int ROWS = 20;
    private static int COLS = 10;
    private static final int CELL_SIZE = 30; //Tamaño de las celdas

    /*Getter configuracion tamaño*/
    public static int getROWS() {
        return ROWS;
    }
    public static int getCOLS() {
        return COLS;
    }
    public static int getCELL_SIZE() {
        return CELL_SIZE;
    }



    /*Color de las piezas
     * Configuracion para el color de las piezas*/
    private static Color COLOR_PIECE_PINT = Color.PINK;
    private static Color COLOR_PIECE_I = Color.CYAN;
    private static Color COLOR_PIECE_O = Color.YELLOW;
    private static Color COLOR_PIECE_T = Color.MAGENTA; // Morado
    private static Color COLOR_PIECE_S = Color.GREEN;
    private static Color COLOR_PIECE_Z = Color.RED;
    private static Color COLOR_PIECE_PIECEMYSTERY = Color.DARK_GRAY;
    private static Color COLOR_PIECE_J = Color.BLUE;
    private static Color COLOR_PIECE_L = Color.ORANGE;

    /*Getter configuracion de colores*/
    public static Color getColorPiecePint() {
        return COLOR_PIECE_PINT;
    }
    public static Color getColorPieceI() {
        return COLOR_PIECE_I;
    }
    public static Color getColorPieceO() {
        return COLOR_PIECE_O;
    }
    public static Color getColorPieceT() {
        return COLOR_PIECE_T;
    }
    public static Color getColorPieceS() {
        return COLOR_PIECE_S;
    }
    public static Color getColorPieceZ() {
        return COLOR_PIECE_Z;
    }
    public static Color getColorPiecePiecemystery() {
        return COLOR_PIECE_PIECEMYSTERY;
    }
    public static Color getColorPieceJ() {
        return COLOR_PIECE_J;
    }
    public static Color getColorPieceL() {
        return COLOR_PIECE_L;
    }



    /*Configuracion de tamaño de tablero
     * Configuracion para el tamaño del juego*/
    private static int WIDTH = 500;
    private static int HEIGHT = 750;

    public static int getWIDTH() {
        return WIDTH;
    }
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /*Configuracion del login*/
    private static boolean LOGIN = false;

    public static boolean getLogin(){
        return LOGIN;
    }


}