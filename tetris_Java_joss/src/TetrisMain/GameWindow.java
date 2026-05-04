package TetrisMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    private BoardPanel boardPanel;
    private String currentPlayer;
    private JLabel scoreLabel;

    public GameWindow(String player) {
        this.currentPlayer = player;
        setTitle("Tetris - " + player);
        setSize(Setting.getWIDTH(), Setting.getHEIGHT());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(210, 210, 210)); // Gris claro general

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel izquierdo: Tablero de juego
        boardPanel = new BoardPanel(this);
        boardPanel.setPreferredSize(new Dimension(300, 600));
        add(boardPanel, BorderLayout.CENTER);

        // Panel derecho: Stats y Puntuación
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(150, 600));

        // Pieza Siguiente
        RoundedPanel nextPiecePanel = new RoundedPanel(new BorderLayout(), new Color(160, 160, 160));
        nextPiecePanel.setPreferredSize(new Dimension(150, 150));
        // Aquí iría la lógica de pintar la siguiente pieza
        
        // Puntuación
        RoundedPanel scorePanel = new RoundedPanel(new BorderLayout(), new Color(160, 160, 160));
        scorePanel.setPreferredSize(new Dimension(150, 60));
        scoreLabel = new JLabel("Puntuacion: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scorePanel.add(scoreLabel, BorderLayout.CENTER);

        // Contenedor superior derecho
        JPanel topRight = new JPanel(new BorderLayout(0, 10));
        topRight.setOpaque(false);
        topRight.add(nextPiecePanel, BorderLayout.NORTH);
        topRight.add(scorePanel, BorderLayout.SOUTH);

        // Estadísticas
        RoundedPanel statsPanel = new RoundedPanel(new FlowLayout(), new Color(160, 160, 160));
        statsPanel.add(new JLabel("Stats"));
        // Aquí se pueden añadir los paneles blancos internos que tienes en tu diseño

        rightPanel.add(topRight, BorderLayout.NORTH);
        rightPanel.add(statsPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);

        // Controles de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boardPanel.handleInput(e.getKeyCode());
            }
        });
        setFocusable(true);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Puntuacion: " + score);
    }

    /**
     * Funcion para parar el juego
     * @param finalScore
     */
    public void gameOver(int finalScore) {
        DatabaseManager.saveRecord(currentPlayer, finalScore);
        JOptionPane.showMessageDialog(this, "Game Over! Puntuación guardada: " + finalScore);
        System.exit(0);
    }
}