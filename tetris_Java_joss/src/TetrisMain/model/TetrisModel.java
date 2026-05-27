package TetrisMain.model;

import java.awt.Color;

public class TetrisModel implements Reiniciable {
    private final int ROWS = Setting.getROWS();
    private final int COLS = Setting.getCOLS();
    private Color[][] grid = new Color[ROWS][COLS];
    private boolean isGameOver = false;

    private PiezaBase currentPiezaBase;
    private PiezaBase nextPiezaBase;

    private int score = 0;
    private int level = Setting.getSTARTING_LEVEL();
    private int linesClearedTotal = 0;
    private int streak = 0;

    @Override
    public void reiniciar() {
        this.grid = new Color[ROWS][COLS];
        this.score = 0;
        this.level = Setting.getSTARTING_LEVEL();
        this.linesClearedTotal = 0;
        this.streak = 0;
        this.isGameOver = false;
        this.nextPiezaBase = PieceFactory.getRandomPiece();
        spawnPiece();
    }

    public TetrisModel() {
        this.nextPiezaBase = PieceFactory.getRandomPiece();
    }

    public void spawnPiece() {
        currentPiezaBase = nextPiezaBase;
        nextPiezaBase = PieceFactory.getRandomPiece();
    }
    public boolean isGameOver() { return isGameOver; }
    public void setGameOver(boolean gameOver) { isGameOver = gameOver; }

    public boolean canMove(int newX, int newY, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardX = newX + j;
                    int boardY = newY + i;
                    if (boardX < 0 || boardX >= COLS || boardY >= ROWS || (boardY >= 0 && grid[boardY][boardX] != null)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void lockPiece() {
        int[][] shape = currentPiezaBase.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardY = currentPiezaBase.getY() + i;
                    int boardX = currentPiezaBase.getX() + j;
                    if (boardY >= 0) grid[boardY][boardX] = currentPiezaBase.getColor();
                }
            }
        }
    }

    public int checkLines() {
        int linesClearedThisTurn = 0;
        for (int i = ROWS - 1; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int y = i; y > 0; y--) {
                    for (int x = 0; x < COLS; x++) {
                        grid[y][x] = grid[y - 1][x];
                    }
                }
                for (int x = 0; x < COLS; x++) grid[0][x] = null;
                linesClearedThisTurn++;
                i++;
            }
        }
        return linesClearedThisTurn;
    }

    public void updateScore(int lines, int multiplicadorPuntuacion) {
        if (lines > 0) {
            streak++;
            int basePoints = 0;
            if (lines == 1) basePoints = 100;
            else if (lines == 2) basePoints = 300;
            else if (lines == 3) basePoints = 500;
            else if (lines >= 4) basePoints = 800;

            double multiplier = 1.0;
            if (streak == 2) multiplier = 1.2;
            else if (streak == 3) multiplier = 1.5;
            else if (streak >= 4) multiplier = Math.pow(2, streak - 3);

            score += (int) (basePoints * multiplier * multiplicadorPuntuacion);
            linesClearedTotal += lines;

            score += (int) (basePoints * multiplier);
            linesClearedTotal += lines;

            int newLevel = (int) Math.floor(Math.sqrt((double) score / 500.0)) + 1;
            if (newLevel > level) {
                level = newLevel;
            }
        } else {
            streak = 0;
        }
    }

    public void setNextPiece(PiezaBase pieza) {
        this.nextPiezaBase = pieza;
    }

    public void addHardDropPoints(int rowsDropped) {
        score += (rowsDropped * 2);
    }
    public void addSoftDropPoints(int rows, int multiplicador) {
        this.score += (rows * multiplicador);
    }

    public Color[][] getGrid() { return grid; }
    public PiezaBase getCurrentPiece() { return currentPiezaBase; }
    public PiezaBase getNextPiece() { return nextPiezaBase; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getLines() { return linesClearedTotal; }
}