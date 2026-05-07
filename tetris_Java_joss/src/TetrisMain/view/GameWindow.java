package TetrisMain.view;

import TetrisMain.model.*;
import TetrisMain.controller.TetrisController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    private BoardPanel boardPanel;
    private TetrisController controller;
    private String currentPlayer;

    private JLabel scoreLabel;
    private JLabel levelLabel;
    private JLabel linesLabel;
    private NextPiecePanel nextPiecePanel;

    public GameWindow(String player) {
        this.currentPlayer = player;
        setTitle("Tetris - Jugador: " + player);
        setSize(Setting.getWIDTH(), Setting.getHEIGHT());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(210, 210, 210));

        setLayout(new BorderLayout(10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nextPiecePanel = new NextPiecePanel();

        // INSTANCIAMOS EL MODELO Y SE LO PASAMOS AL PANEL
        TetrisModel model = new TetrisModel();
        boardPanel = new BoardPanel(model);
        boardPanel.setPreferredSize(new Dimension(
                (Setting.getCOLS() * Setting.getCELL_SIZE()) + 20,
                (Setting.getROWS() * Setting.getCELL_SIZE()) + 20
        ));
        add(boardPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(170, 750));

        JPanel topRight = new JPanel(new BorderLayout(0, 10));
        topRight.setOpaque(false);
        topRight.add(nextPiecePanel, BorderLayout.NORTH);

        RoundedPanel statsPanel = new RoundedPanel(new GridLayout(3, 1, 5, 5), new Color(160, 160, 160));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        Font hudFont = new Font("Arial", Font.BOLD, 15);
        scoreLabel = new JLabel("Puntos: 0", SwingConstants.CENTER);
        levelLabel = new JLabel("Nivel: " + Setting.getSTARTING_LEVEL(), SwingConstants.CENTER);
        linesLabel = new JLabel("Líneas: 0", SwingConstants.CENTER);

        scoreLabel.setFont(hudFont);
        levelLabel.setFont(hudFont);
        linesLabel.setFont(hudFont);

        statsPanel.add(scoreLabel);
        statsPanel.add(levelLabel);
        statsPanel.add(linesLabel);

        topRight.add(statsPanel, BorderLayout.SOUTH);

        RoundedPanel controlsPanel = new RoundedPanel(new GridLayout(5, 1, 2, 2), new Color(160, 160, 160));
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        controlsPanel.add(new JLabel("CONTROLES", SwingConstants.CENTER));
        controlsPanel.add(new JLabel("← / → : Mover", SwingConstants.CENTER));
        controlsPanel.add(new JLabel("↑ : Rotar", SwingConstants.CENTER));
        controlsPanel.add(new JLabel("↓ : Soft Drop", SwingConstants.CENTER));
        controlsPanel.add(new JLabel("Espacio : Hard Drop", SwingConstants.CENTER));

        rightPanel.add(topRight, BorderLayout.NORTH);
        rightPanel.add(controlsPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);

        // CREAMOS EL CONTROLADOR
        this.controller = new TetrisController(model, this);

        // PASAMOS LOS EVENTOS DE TECLADO AL CONTROLADOR
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { controller.handleKeyPress(e.getKeyCode()); }
            @Override
            public void keyReleased(KeyEvent e) { controller.handleKeyRelease(e.getKeyCode()); }
        });
        setFocusable(true);
    }

    // EL GETTER QUE FALTABA
    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    public void updateStats(int score, int level, int lines) {
        scoreLabel.setText("Puntos: " + score);
        levelLabel.setText("Nivel: " + level);
        linesLabel.setText("Líneas: " + lines);
    }

    public void updateNextPiece(Piece piece) {
        if (nextPiecePanel != null) {
            nextPiecePanel.setNextPiece(piece);
        }
    }

    public void gameOver(int finalScore) {
        DatabaseManager.saveRecord(currentPlayer, finalScore);
        JOptionPane.showMessageDialog(this, "Game Over! Puntuación guardada: " + finalScore);
        this.dispose();
        new MainMenuWindow(currentPlayer).setVisible(true);
    }
}