package TetrisMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BoardPanel extends RoundedPanel {
    private final int ROWS = Setting.getROWS();
    private final int COLS = Setting.getCOLS();
    private final int CELL_SIZE = Setting.getCELL_SIZE();
    
    private Color[][] grid = new Color[ROWS][COLS];
    private Piece currentPiece;
    private Timer timer;
    private GameWindow parent;
    private int score = 0;

    public BoardPanel(GameWindow parent) {
        super(null, new Color(180, 180, 180)); // Color base de la cuadrícula
        this.parent = parent;
        spawnPiece();

        // Game Loop (caída automática)
        timer = new Timer(500, e -> {
            moveDown();
            repaint();
        });
        timer.start();
    }


    private void spawnPiece() {
        currentPiece = PieceFactory.getRandomPiece();
        if (!canMove(currentPiece.getX(), currentPiece.getY(), currentPiece.getShape())) {
            timer.stop();
            parent.gameOver(score);
        }
    }

    public void handleInput(int keyCode) {
        if (currentPiece == null) return;

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (canMove(currentPiece.getX() - 1, currentPiece.getY(), currentPiece.getShape()))
                    currentPiece.setX(currentPiece.getX() - 1);
                break;
            case KeyEvent.VK_RIGHT:
                if (canMove(currentPiece.getX() + 1, currentPiece.getY(), currentPiece.getShape()))
                    currentPiece.setX(currentPiece.getX() + 1);
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_UP: // Rotar
                currentPiece.rotate();
                if (!canMove(currentPiece.getX(), currentPiece.getY(), currentPiece.getShape())) {
                    for(int i=0; i<3; i++) currentPiece.rotate();
                }
                break;
            case KeyEvent.VK_SPACE:
                hardDrop();
                break;
        }
        repaint();
    }

    private void moveDown() {
        if (canMove(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getShape())) {
            currentPiece.setY(currentPiece.getY() + 1);
        } else {
            lockPiece();
            checkLines();
            spawnPiece();
        }
    }

    private boolean canMove(int newX, int newY, int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardX = newX + j;
                    int boardY = newY + i;
                    // Chequear límites y colisiones con piezas ancladas
                    if (boardX < 0 || boardX >= COLS || boardY >= ROWS || (boardY >= 0 && grid[boardY][boardX] != null)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void lockPiece() {
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

    private void checkLines() {
        for (int i = ROWS - 1; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                // Mover todas las filas de arriba hacia abajo
                for (int y = i; y > 0; y--) {
                    for (int x = 0; x < COLS; x++) {
                        grid[y][x] = grid[y - 1][x];
                    }
                }
                // Limpiar la fila superior
                for (int x = 0; x < COLS; x++) grid[0][x] = null;
                
                score += 100;
                parent.updateScore(score);
                i++; // Volver a comprobar la misma fila, ya que cayeron bloques nuevos
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Pinta el fondo redondeado y el borde grueso

        Graphics2D g2d = (Graphics2D) g;
        // Compensación por el grosor del borde de RoundedPanel (aprox 4px)
        int offsetX = 10; 
        int offsetY = 10;

        // Dibujar la cuadrícula (el fondo)
        g2d.setColor(new Color(130, 130, 130)); // Gris oscuro para las líneas
        for (int i = 0; i <= ROWS; i++) {
            g2d.drawLine(offsetX, offsetY + i * CELL_SIZE, offsetX + COLS * CELL_SIZE, offsetY + i * CELL_SIZE);
        }
        for (int j = 0; j <= COLS; j++) {
            g2d.drawLine(offsetX + j * CELL_SIZE, offsetY, offsetX + j * CELL_SIZE, offsetY + ROWS * CELL_SIZE);
        }

        // Dibujar piezas ancladas
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] != null) {
                    drawBlock(g2d, offsetX + j * CELL_SIZE, offsetY + i * CELL_SIZE, grid[i][j]);
                }
            }
        }

        // Dibujar la pieza activa
        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        drawBlock(g2d, offsetX + (currentPiece.getX() + j) * CELL_SIZE, 
                                       offsetY + (currentPiece.getY() + i) * CELL_SIZE, currentPiece.getColor());
                    }
                }
            }
        }
    }

    /**
     * Diseño de borde en el juego y dibujado de las piezas
     * @param g2d
     * @param x
     * @param y
     * @param color
     */
    private void drawBlock(Graphics2D g2d, int x, int y, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
        // Borde interior del bloque para darle volumen retro
        g2d.setColor(color.darker());
        g2d.drawRect(x + 1, y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
    }

    /**
     * Funcion que hace que la pieza caiga hasta detectar una colicion llamando a la funcion canMove
     */
    private void hardDrop() {
        while (canMove(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getShape())) {
            currentPiece.setY(currentPiece.getY() + 1);
            score += 2;
        }

        // Una vez que toca fondo, la bloqueamos y generamos la siguiente
        lockPiece();
        checkLines();
        spawnPiece();
        parent.updateScore(score); // Actualizamos la UI si dimos puntos extra
    }
}