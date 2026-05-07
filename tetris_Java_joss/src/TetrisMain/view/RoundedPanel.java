package TetrisMain.view;

import javax.swing.*;
import java.awt.*;



public class RoundedPanel extends JPanel {
    private int cornerRadius = 15;
    private Color backgroundColor;
    private Color borderColor = new Color(80, 80, 80); // Gris oscuro de los bordes
    private int borderThickness = 4;


    /**
     * Metodo para estrablecer la opaques y color del fondo
     * @param layout
     * @param bgColor
     */
    public RoundedPanel(LayoutManager layout, Color bgColor) {
        super(layout);
        this.backgroundColor = bgColor;
        setOpaque(false);
    }


    /**
     * Reescribe el metodo PaintComponent para dibujar borde
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo
        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(borderThickness, borderThickness, 
                getWidth() - borderThickness*2, getHeight() - borderThickness*2, cornerRadius, cornerRadius);

        // Dibujar el borde grueso
        graphics.setColor(borderColor);
        graphics.setStroke(new BasicStroke(borderThickness));
        graphics.drawRoundRect(borderThickness/2, borderThickness/2, 
                getWidth() - borderThickness, getHeight() - borderThickness, cornerRadius, cornerRadius);

        graphics.dispose();
    }
}