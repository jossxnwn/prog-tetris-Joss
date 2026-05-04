package TetrisMain;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (Setting.getLogin()){
                LoginWindow login = new LoginWindow();
                login.setVisible(true);
            }else {
                new GameWindow("userTest").setVisible(true);
            }

        });
    }
}