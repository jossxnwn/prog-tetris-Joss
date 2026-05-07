package TetrisMain.model;

import java.awt.Color;

public class Setting {
    private static int ROWS = 24;
    private static int COLS = 10;
    private static int CELL_SIZE = 25; // Variable modificable

    public static int getROWS() { return ROWS; }
    public static int getCOLS() { return COLS; }
    public static int getCELL_SIZE() { return CELL_SIZE; }
    public static void setCELL_SIZE(int size) { CELL_SIZE = size; }

    private static Color COLOR_PIECE_PINT = Color.PINK;
    private static Color COLOR_PIECE_I = Color.CYAN;
    private static Color COLOR_PIECE_O = Color.YELLOW;
    private static Color COLOR_PIECE_T = Color.MAGENTA;
    private static Color COLOR_PIECE_S = Color.GREEN;
    private static Color COLOR_PIECE_Z = Color.RED;
    private static Color COLOR_PIECE_PIECEMYSTERY = Color.DARK_GRAY;
    private static Color COLOR_PIECE_J = Color.BLUE;
    private static Color COLOR_PIECE_L = Color.ORANGE;

    public static Color getColorPiecePint() { return COLOR_PIECE_PINT; }
    public static Color getColorPieceI() { return COLOR_PIECE_I; }
    public static Color getColorPieceO() { return COLOR_PIECE_O; }
    public static Color getColorPieceT() { return COLOR_PIECE_T; }
    public static Color getColorPieceS() { return COLOR_PIECE_S; }
    public static Color getColorPieceZ() { return COLOR_PIECE_Z; }
    public static Color getColorPiecePiecemystery() { return COLOR_PIECE_PIECEMYSTERY; }
    public static Color getColorPieceJ() { return COLOR_PIECE_J; }
    public static Color getColorPieceL() { return COLOR_PIECE_L; }

    private static int WIDTH = 480;
    private static int HEIGHT = 680;

    public static int getWIDTH() { return WIDTH; }
    public static int getHEIGHT() { return HEIGHT; }
    public static void setWIDTH(int w) { WIDTH = w; }
    public static void setHEIGHT(int h) { HEIGHT = h; }

    // NUEVO: Control de Nivel Inicial
    private static int STARTING_LEVEL = 1;
    public static int getSTARTING_LEVEL() { return STARTING_LEVEL; }
    public static void setSTARTING_LEVEL(int level) { STARTING_LEVEL = level; }

    private static boolean LOGIN = false;
    public static boolean getLogin(){ return LOGIN; }
}