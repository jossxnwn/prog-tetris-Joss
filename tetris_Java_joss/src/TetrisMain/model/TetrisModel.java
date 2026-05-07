package TetrisMain.model;

import java.awt.Color;

public class TetrisModel {
    private final int ROWS = Setting.getROWS();
    private final int COLS = Setting.getCOLS();
    private Color[][] grid = new Color[ROWS][COLS];

    private Piece currentPiece;
    private Piece nextPiece;

    private int score = 0;
    private int level = Setting.getSTARTING_LEVEL();
    private int linesClearedTotal = 0;
    private int streak = 0;

    public TetrisModel() {
        this.nextPiece = PieceFactory.getRandomPiece();
    }

    public void spawnPiece() {
        currentPiece = nextPiece;
        nextPiece = PieceFactory.getRandomPiece();
    }

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
        int[][] shape = currentPiece.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardY = currentPiece.getY() + i;
                    int boardX = currentPiece.getX() + j;
                    if (boardY >= 0) grid[boardY][boardX] = currentPiece.getColor();
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

    public void updateScore(int lines) {
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

    public void addHardDropPoints(int rowsDropped) {
        score += (rowsDropped * 2);
    }

    public Color[][] getGrid() { return grid; }
    public Piece getCurrentPiece() { return currentPiece; }
    public Piece getNextPiece() { return nextPiece; }
    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getLines() { return linesClearedTotal; }
}