package TetrisMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BoardPanel extends RoundedPanel {
    private final int ROWS = Setting.getROWS();
    private final int COLS = Setting.getCOLS();

    private Color[][] grid = new Color[ROWS][COLS];
    private Piece currentPiece;
    private Piece nextPiece;
    private Timer timer;
    private Timer inputTimer; // NUEVO: Temporizador para controles fluidos
    private GameWindow parent;

    private int score = 0;
    private int level = Setting.getSTARTING_LEVEL();
    private int linesClearedTotal = 0;
    private int streak = 0;
    private int shakeIntensity = 0;

    // --- VARIABLES DEL SISTEMA DAS Y ARR ---
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;

    private long leftPressTime = 0;
    private long rightPressTime = 0;
    private long downPressTime = 0;

    private long lastLeftAuto = 0;
    private long lastRightAuto = 0;
    private long lastDownAuto = 0;

    // Configuración de velocidad (Milisegundos)
    private final int DAS = 160;  // Tiempo que debe mantenerse pulsado antes de acelerar
    private final int ARR = 30;   // Velocidad a la que se mueve una vez acelerado
    private final int SOFT_DROP_RATE = 30; // Velocidad de caída al mantener flecha abajo

    public BoardPanel(GameWindow parent) {
        super(null, new Color(180, 180, 180));
        this.parent = parent;

        this.nextPiece = PieceFactory.getRandomPiece();
        spawnPiece();

        // Game Loop original (Caída por gravedad)
        timer = new Timer(800, e -> {
            moveDown();
            repaint();
        });
        updateSpeed();
        timer.start();

        // --- NUEVO: Game Loop de Input (100 FPS) para un control perfecto ---
        inputTimer = new Timer(10, e -> processHeldKeys());
        inputTimer.start();
    }

    public void stopTimers() {
        if (timer != null) timer.stop();
        if (inputTimer != null) inputTimer.stop();
    }

    private void spawnPiece() {
        currentPiece = this.nextPiece;
        this.nextPiece = PieceFactory.getRandomPiece();

        if (!canMove(currentPiece.getX(), currentPiece.getY(), currentPiece.getShape())) {
            stopTimers();
            parent.gameOver(score);
        }
        parent.updateNextPiece(this.nextPiece);
    }

    // --- NUEVO: Procesa las teclas si se mantienen pulsadas ---
    private void processHeldKeys() {
        if (currentPiece == null) return;
        long now = System.currentTimeMillis();
        boolean moved = false;

        if (leftPressed) {
            if (now - leftPressTime >= DAS && now - lastLeftAuto >= ARR) {
                moveLeft();
                lastLeftAuto = now;
                moved = true;
            }
        }
        if (rightPressed) {
            if (now - rightPressTime >= DAS && now - lastRightAuto >= ARR) {
                moveRight();
                lastRightAuto = now;
                moved = true;
            }
        }
        if (downPressed) {
            if (now - downPressTime >= 0 && now - lastDownAuto >= SOFT_DROP_RATE) {
                moveDown();
                lastDownAuto = now;
                moved = true;
            }
        }

        if (moved) repaint();
    }

    public void handleInput(int keyCode) {
        if (currentPiece == null) return;
        long now = System.currentTimeMillis();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (!leftPressed) { // Evita el repeat del Sistema Operativo
                    leftPressed = true;
                    leftPressTime = now;
                    lastLeftAuto = now;
                    moveLeft();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!rightPressed) {
                    rightPressed = true;
                    rightPressTime = now;
                    lastRightAuto = now;
                    moveRight();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!downPressed) {
                    downPressed = true;
                    downPressTime = now;
                    lastDownAuto = now;
                    moveDown();
                }
                break;
            case KeyEvent.VK_UP:
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

    // --- NUEVO: Detecta cuando soltamos la tecla ---
    public void handleKeyRelease(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT: leftPressed = false; break;
            case KeyEvent.VK_RIGHT: rightPressed = false; break;
            case KeyEvent.VK_DOWN: downPressed = false; break;
        }
    }

    private void moveLeft() {
        if (canMove(currentPiece.getX() - 1, currentPiece.getY(), currentPiece.getShape())) {
            currentPiece.setX(currentPiece.getX() - 1);
        }
    }

    private void moveRight() {
        if (canMove(currentPiece.getX() + 1, currentPiece.getY(), currentPiece.getShape())) {
            currentPiece.setX(currentPiece.getX() + 1);
        }
    }

    private void moveDown() {
        if (canMove(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getShape())) {
            currentPiece.setY(currentPiece.getY() + 1);
        } else {
            lockPiece();
            int lines = checkLines();

            if (lines > 0) {
                streak++;
                int basePoints = 0;
                if (lines == 1) basePoints = 100;
                else if (lines == 2) basePoints = 300;
                else if (lines == 3) basePoints = 500;
                else if (lines == 4) basePoints = 800;

                double multiplier = 1.0;
                if (streak == 2) multiplier = 1.2;
                else if (streak == 3) multiplier = 1.5;
                else if (streak >= 4) multiplier = Math.pow(2, streak - 3);

                score += (int) (basePoints * multiplier);
                linesClearedTotal += lines;

                int newLevel = (int) Math.floor(Math.sqrt((double) score / 500.0)) + 1;
                if (newLevel > level) {
                    level = newLevel;
                    updateSpeed();
                }
            } else {
                streak = 0;
            }

            parent.updateStats(score, level, linesClearedTotal);
            spawnPiece();
        }
    }

    private void updateSpeed() {
        int delay = 800;
        if (level == 2) delay = 680;
        else if (level == 3) delay = 580;
        else if (level == 4) delay = 500;
        else if (level == 5) delay = 420;
        else if (level == 6) delay = 360;
        else if (level == 7) delay = 300;
        else if (level == 8) delay = 260;
        else if (level == 9) delay = 220;
        else if (level == 10) delay = 190;
        else if (level == 11) delay = 160;
        else if (level == 12) delay = 140;
        else if (level >= 15) delay = 80;

        timer.setDelay(delay);
    }

    private boolean canMove(int newX, int newY, int[][] shape) {
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

    private int checkLines() {
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

    private void hardDrop() {
        int startY = currentPiece.getY();

        while (canMove(currentPiece.getX(), currentPiece.getY() + 1, currentPiece.getShape())) {
            currentPiece.setY(currentPiece.getY() + 1);
        }

        if (currentPiece.getY() > startY) {
            shakeIntensity = 8;
            Timer effectTimer = new Timer(16, null);
            effectTimer.addActionListener(e -> {
                shakeIntensity -= 1;
                if (shakeIntensity <= 0) {
                    shakeIntensity = 0;
                    ((Timer)e.getSource()).stop();
                }
                repaint();
            });
            effectTimer.start();
        }

        moveDown();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int currentCellSize = Math.min((panelWidth - 20) / COLS, (panelHeight - 20) / ROWS);

        int offsetX = (panelWidth - (COLS * currentCellSize)) / 2;
        int offsetY = (panelHeight - (ROWS * currentCellSize)) / 2;

        if (shakeIntensity > 0) {
            offsetX += (Math.random() * shakeIntensity) - (shakeIntensity / 2.0);
            offsetY += (Math.random() * shakeIntensity) - (shakeIntensity / 2.0);
        }

        g2d.setColor(new Color(130, 130, 130));
        for (int i = 0; i <= ROWS; i++) {
            g2d.drawLine(offsetX, offsetY + i * currentCellSize, offsetX + COLS * currentCellSize, offsetY + i * currentCellSize);
        }
        for (int j = 0; j <= COLS; j++) {
            g2d.drawLine(offsetX + j * currentCellSize, offsetY, offsetX + j * currentCellSize, offsetY + ROWS * currentCellSize);
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] != null) {
                    drawBlock(g2d, offsetX + j * currentCellSize, offsetY + i * currentCellSize, grid[i][j], currentCellSize);
                }
            }
        }

        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();

            int ghostY = currentPiece.getY();
            while (canMove(currentPiece.getX(), ghostY + 1, shape)) {
                ghostY++;
            }

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        drawGhostBlock(g2d, offsetX + (currentPiece.getX() + j) * currentCellSize,
                                offsetY + (ghostY + i) * currentCellSize, currentPiece.getColor(), currentCellSize);
                    }
                }
            }

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        drawBlock(g2d, offsetX + (currentPiece.getX() + j) * currentCellSize,
                                offsetY + (currentPiece.getY() + i) * currentCellSize, currentPiece.getColor(), currentCellSize);
                    }
                }
            }
        }
    }

    private void drawBlock(Graphics2D g2d, int x, int y, Color color, int size) {
        g2d.setColor(color);
        g2d.fillRect(x + 1, y + 1, size - 2, size - 2);
        g2d.setColor(color.darker());
        g2d.drawRect(x + 1, y + 1, size - 2, size - 2);
    }

    private void drawGhostBlock(Graphics2D g2d, int x, int y, Color baseColor, int size) {
        g2d.setColor(new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 60));
        g2d.fillRect(x + 1, y + 1, size - 2, size - 2);
        g2d.setColor(new Color(255, 255, 255, 120));
        g2d.drawRect(x + 1, y + 1, size - 2, size - 2);
    }
}