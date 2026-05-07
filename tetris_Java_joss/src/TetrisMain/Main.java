package TetrisMain;

import TetrisMain.view.LoginWindow;
import TetrisMain.view.MainMenuWindow;
import TetrisMain.model.Setting;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (Setting.getLogin()){
                LoginWindow login = new LoginWindow();
                login.setVisible(true);
            } else {
                // Abre el menú en lugar de la ventana de juego directamente
                new MainMenuWindow("userTest").setVisible(true);
            }
        });
    }
}