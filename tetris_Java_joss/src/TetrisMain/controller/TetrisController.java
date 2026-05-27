package TetrisMain.controller;

import TetrisMain.model.*;
import TetrisMain.view.*;
import javax.swing.Timer;
import java.awt.event.KeyEvent;

public class TetrisController {
    private TetrisModel model;
    private GameWindow view;
    private Timer gameTimer;
    private Timer inputTimer;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;

    private long leftPressTime = 0;
    private long rightPressTime = 0;
    private long downPressTime = 0;

    private long lastLeftAuto = 0;
    private long lastRightAuto = 0;
    private long lastDownAuto = 0;

    private final int DAS = 160;
    private final int ARR = 30;
    private final int SOFT_DROP_RATE = 30;

    private int reiniciosDisponibles = 5;
    private long lastRPressTime = 0;



    public TetrisController(TetrisModel model, GameWindow view) {
        this.model = model;
        this.view = view;
        model.spawnPiece();
        view.updateNextPiece(model.getNextPiece());
        initTimers();
    }


    private void initTimers() {
        gameTimer = new Timer(800, e -> updateGame());
        inputTimer = new Timer(10, e -> processInput());

        updateSpeed();
        gameTimer.start();
        inputTimer.start();
    }

    public void stopTimers() {
        if (gameTimer != null) gameTimer.stop();
        if (inputTimer != null) inputTimer.stop();
    }

    private void processInput() {
        if (model.getCurrentPiece() == null) return;
        long now = System.currentTimeMillis();
        boolean moved = false;

        if (leftPressed && now - leftPressTime >= DAS && now - lastLeftAuto >= ARR) {
            if (model.canMove(model.getCurrentPiece().getX() - 1, model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
                model.getCurrentPiece().setX(model.getCurrentPiece().getX() - 1);
            }
            lastLeftAuto = now;
            moved = true;
        }
        if (rightPressed && now - rightPressTime >= DAS && now - lastRightAuto >= ARR) {
            if (model.canMove(model.getCurrentPiece().getX() + 1, model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
                model.getCurrentPiece().setX(model.getCurrentPiece().getX() + 1);
            }
            lastRightAuto = now;
            moved = true;
        }
        if (downPressed && now - downPressTime >= 0 && now - lastDownAuto >= SOFT_DROP_RATE) {
            updateGame();
            lastDownAuto = now;
            moved = true;
        }

        view.getBoardPanel().reduceShake(); // Reduce el temblor poco a poco
        view.getBoardPanel().repaint();
    }

    private void updateGame() {
        if (model.getCurrentPiece() == null) return;
        if (model.canMove(model.getCurrentPiece().getX(), model.getCurrentPiece().getY() + 1, model.getCurrentPiece().getShape())) {
            model.getCurrentPiece().setY(model.getCurrentPiece().getY() + 1);
        } else {
            handleCollision();
        }
        view.getBoardPanel().repaint();
    }

    private void handleCollision() {
        model.lockPiece();
        int lines = model.checkLines();
        // (El método updateScore cambiará en el Ejercicio 3, lo actualizamos luego)
        model.updateScore(lines, model.getCurrentPiece().getMultiplicadorPuntuacion());
        updateSpeed();

        model.spawnPiece();

        if (!model.canMove(model.getCurrentPiece().getX(), model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
            stopTimers();
            model.setGameOver(true); // AHORA MARCAMOS EL GAME OVER
            return;
        }

        view.updateStats(model.getScore(), model.getLevel(), model.getLines());
        view.updateNextPiece(model.getNextPiece());
    }

    public void handleKeyPress(int keyCode) throws MatrizInvalidaException {
        if (model.getCurrentPiece() == null) return;

        // --- AÑADE ESTAS 3 LÍNEAS AQUÍ ---
        // Si hay Game Over, bloqueamos todas las teclas EXCEPTO la tecla 'R'
        if (model.isGameOver() && keyCode != KeyEvent.VK_R) {
            stopTimers();
            model.setGameOver(true); // AHORA MARCAMOS EL GAME OVER
            javax.swing.JOptionPane.showMessageDialog(view,
                    "¡GAME OVER!\nPuntuación final: " + model.getScore() + "\nPulsa 'R' para reiniciar.");
            // -----------------------------
            return;
        }
        // ---------------------------------

        long now = System.currentTimeMillis();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (!leftPressed) {
                    leftPressed = true; leftPressTime = now; lastLeftAuto = now;
                    if (model.canMove(model.getCurrentPiece().getX() - 1, model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
                        model.getCurrentPiece().setX(model.getCurrentPiece().getX() - 1);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!rightPressed) {
                    rightPressed = true; rightPressTime = now; lastRightAuto = now;
                    if (model.canMove(model.getCurrentPiece().getX() + 1, model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
                        model.getCurrentPiece().setX(model.getCurrentPiece().getX() + 1);
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!downPressed) {
                    downPressed = true; downPressTime = now; lastDownAuto = now;
                    updateGame();
                }
                break;
            case KeyEvent.VK_UP:
                model.getCurrentPiece().rotate();
                if (!model.canMove(model.getCurrentPiece().getX(), model.getCurrentPiece().getY(), model.getCurrentPiece().getShape())) {
                    for(int i=0; i<3; i++) model.getCurrentPiece().rotate();
                }
                break;
            case KeyEvent.VK_SPACE:
                int startY = model.getCurrentPiece().getY();
                while (model.canMove(model.getCurrentPiece().getX(), model.getCurrentPiece().getY() + 1, model.getCurrentPiece().getShape())) {
                    model.getCurrentPiece().setY(model.getCurrentPiece().getY() + 1);
                }
                // Si la pieza cayó, dar puntos extra y activar temblor
                if (model.getCurrentPiece().getY() > startY) {
                    model.addHardDropPoints(model.getCurrentPiece().getY() - startY);
                    view.getBoardPanel().triggerShake();
                }
                updateGame();
                break;
            case KeyEvent.VK_R:
                if (reiniciosDisponibles > 0) {
                    // Eliminamos la línea "long now = System.currentTimeMillis();" porque ya existe arriba

                    if (model.isGameOver()) {
                        // Un solo toque si es Game Over
                        ejecutarReinicio();
                    } else {
                        // Doble toque si el juego está en curso (ej: 500 ms de margen)
                        if (now - lastRPressTime < 500) {
                            ejecutarReinicio();
                            lastRPressTime = 0; // Resetear el tiempo
                        } else {
                            lastRPressTime = now;
                        }
                    }
                }
                break;
            case KeyEvent.VK_1:
                try{
                    model.setNextPiece(new TetrisMain.model.Pieces.PiezaE1());
                    view.updateNextPiece(model.getNextPiece());
                }catch (MatrizInvalidaException e){
                    System.err.println(e.getMessage());
                }
                break;

            case KeyEvent.VK_2:
                try{
                    model.setNextPiece(new TetrisMain.model.Pieces.PiezaE2());
                    view.updateNextPiece(model.getNextPiece());
                } catch (MatrizInvalidaException e){
                    System.err.println(e.getMessage());
                }
        }
        view.getBoardPanel().repaint();
    }

    public void handleKeyRelease(int keyCode) {
        if (model.isGameOver()) return;
        switch (keyCode) {
            case KeyEvent.VK_LEFT: leftPressed = false; break;
            case KeyEvent.VK_RIGHT: rightPressed = false; break;
            case KeyEvent.VK_DOWN: downPressed = false; break;
        }
    }

    public void updateSpeed() {
        int delay = 800;
        int level = model.getLevel();
        if (level == 2) delay = 680;
        else if (level == 3) delay = 580;
        else if (level == 4) delay = 500;
        else if (level == 5) delay = 420;
        else if (level == 6) delay = 360;
        else if (level == 7) delay = 300;
        else if (level == 8) delay = 260;
        else if (level >= 9) delay = 200; // Puedes ajustarlo

        if(gameTimer != null) gameTimer.setDelay(delay);
    }
    private void ejecutarReinicio() {
        model.reiniciar();
        reiniciosDisponibles--;
        view.updateStats(model.getScore(), model.getLevel(), model.getLines());
        view.updateNextPiece(model.getNextPiece());

        gameTimer.start();
        inputTimer.start();
    }

}