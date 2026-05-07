package TetrisMain.view;

import TetrisMain.model.*;
import java.awt.*;

public class BoardPanel extends RoundedPanel {
    private TetrisModel model;
    private int shakeIntensity = 0;

    public BoardPanel(TetrisModel model) {
        super(null, new Color(180, 180, 180));
        this.model = model;
    }

    public void triggerShake() {
        this.shakeIntensity = 8;
    }

    public void reduceShake() {
        if (shakeIntensity > 0) shakeIntensity--;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ROWS = Setting.getROWS();
        int COLS = Setting.getCOLS();

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

        Color[][] grid = model.getGrid();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] != null) {
                    drawBlock(g2d, offsetX + j * currentCellSize, offsetY + i * currentCellSize, grid[i][j], currentCellSize);
                }
            }
        }

        Piece currentPiece = model.getCurrentPiece();
        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();

            int ghostY = currentPiece.getY();
            while (model.canMove(currentPiece.getX(), ghostY + 1, shape)) {
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