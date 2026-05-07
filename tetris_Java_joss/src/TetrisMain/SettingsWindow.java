package TetrisMain;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JDialog {
    public SettingsWindow(JFrame parent) {
        super(parent, "Configuración del Juego", true);
        setSize(320, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 10, 20));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(new JLabel("Nivel Inicial:"));
        JSpinner spinLevel = new JSpinner(new SpinnerNumberModel(Setting.getSTARTING_LEVEL(), 1, 15, 1));
        add(spinLevel);

        add(new JLabel("Tamaño Casillas (px):"));
        JSpinner spinCell = new JSpinner(new SpinnerNumberModel(Setting.getCELL_SIZE(), 15, 50, 1));
        add(spinCell);

        JButton btnSave = new JButton("Guardar");
        btnSave.addActionListener(e -> {
            Setting.setSTARTING_LEVEL((int) spinLevel.getValue());
            int newCellSize = (int) spinCell.getValue();
            Setting.setCELL_SIZE(newCellSize);

            // Ajustamos el tamaño de la ventana dinámicamente según el tamaño de la casilla elegida
            int newWidth = (Setting.getCOLS() * newCellSize) + 230;
            int newHeight = (Setting.getROWS() * newCellSize) + 80;

            Setting.setWIDTH(newWidth);
            Setting.setHEIGHT(newHeight);

            JOptionPane.showMessageDialog(this, "Guardado con éxito.\nNueva resolución: " + newWidth + "x" + newHeight);
            dispose();
        });

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());

        add(btnSave);
        add(btnCancel);
    }
}