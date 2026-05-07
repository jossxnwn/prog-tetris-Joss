package TetrisMain.view;

import TetrisMain.model.Piece;

import java.awt.*;

public class NextPiecePanel extends RoundedPanel {
    private Piece nextPiece;

    public NextPiecePanel() {
        super(null, new Color(160, 160, 160));
        setPreferredSize(new Dimension(150, 150));
    }

    public void setNextPiece(Piece piece) {
        this.nextPiece = piece;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (nextPiece == null) return;

        Graphics2D g2d = (Graphics2D) g;
        int[][] shape = nextPiece.getShape();

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculamos el tamaño para que una matriz 4x4 quepa perfectamente
        int currentCellSize = Math.min((panelWidth - 20) / 4, (panelHeight - 20) / 4);

        int pieceWidth = shape[0].length * currentCellSize;
        int pieceHeight = shape.length * currentCellSize;

        // Centramos dentro de su propio panel
        int startX = (panelWidth - pieceWidth) / 2;
        int startY = (panelHeight - pieceHeight) / 2;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    drawBlock(g2d, startX + (j * currentCellSize), startY + (i * currentCellSize), nextPiece.getColor(), currentCellSize);
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
}