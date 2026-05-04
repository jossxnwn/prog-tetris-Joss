package TetrisMain;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    public LoginWindow() {
        setTitle("Tetris Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel panelUser = new JPanel();
        panelUser.add(new JLabel("Usuario:"));
        userField = new JTextField(15);
        panelUser.add(userField);

        JPanel panelPass = new JPanel();
        panelPass.add(new JLabel("Contraseña:"));
        passField = new JPasswordField(15);
        panelPass.add(passField);

        JButton loginButton = new JButton("Entrar");
        loginButton.addActionListener(e -> checkLogin());

        add(panelUser);
        add(panelPass);
        add(loginButton);
    }

    private void checkLogin() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals("test") && pass.equals("test123")) {
            JOptionPane.showMessageDialog(this, "Login exitoso");
            this.dispose(); // Cierra el login
            new GameWindow(user).setVisible(true); // Abre el juego
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}