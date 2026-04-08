package TetrisMain.img; /**
 * Author: Josue Francis Sayritupac Izquierdo
 * Email: a25josuesi@iesantonlosada.gal
 * EmailPersonal: Josue108125@gmail.com
 * Date: 26/3/26
 */
/*indice*/


import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // IMPORTANTE: crear la GUI en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Tetris");

            // Qué hacer al cerrar la ventana (EXIT_ON_CLOSE cierra la JVM)
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Añadir un componente
            ventana.add(new JLabel("¡Hola, Swing!"));

            // Tamaño de la ventana en píxeles
            ventana.setSize(679, 1080);

            // Centrar en pantalla
            ventana.setLocationRelativeTo(null);


            // Mostrar la ventana
            ventana.setVisible(true);
        });
    }
}
