package TetrisMain;

import javax.swing.*;
import java.awt.*;

public class MainMenuWindow extends JFrame {
    private String user;

    public MainMenuWindow(String user) {
        this.user = user;
        setTitle("Tetris - Menú Principal");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(210, 210, 210));

        setLayout(new GridLayout(4, 1, 20, 20));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel title = new JLabel("TETRIS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        add(title);

        JButton btnPlay = new JButton("Iniciar Juego");
        btnPlay.setFont(new Font("Arial", Font.BOLD, 20));
        btnPlay.addActionListener(e -> {
            new GameWindow(this.user).setVisible(true);
            this.dispose(); // Cierra el menú al jugar
        });
        add(btnPlay);

        JButton btnRecords = new JButton("Ver Récords");
        btnRecords.setFont(new Font("Arial", Font.BOLD, 20));
        btnRecords.addActionListener(e -> new RecordsWindow(this).setVisible(true));
        add(btnRecords);

        JButton btnConfig = new JButton("Configuración");
        btnConfig.setFont(new Font("Arial", Font.BOLD, 20));
        btnConfig.addActionListener(e -> new SettingsWindow(this).setVisible(true));
        add(btnConfig);
    }
}