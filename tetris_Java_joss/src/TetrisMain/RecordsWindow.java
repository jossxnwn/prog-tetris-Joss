package TetrisMain;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecordsWindow extends JDialog {
    public RecordsWindow(JFrame parent) {
        super(parent, "Ranking de Récords", true);
        setSize(300, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        List<String> records = DatabaseManager.getRecords();
        DefaultListModel<String> model = new DefaultListModel<>();

        // Ordenamos los récords de mayor a menor puntuación automáticamente
        records.sort((r1, r2) -> {
            try {
                int s1 = Integer.parseInt(r1.split(",")[1]);
                int s2 = Integer.parseInt(r2.split(",")[1]);
                return Integer.compare(s2, s1);
            } catch (Exception e) { return 0; }
        });

        for (int i = 0; i < records.size(); i++) {
            String[] parts = records.get(i).split(",");
            if(parts.length == 2) {
                model.addElement((i + 1) + ". " + parts[0] + " - " + parts[1] + " pts");
            }
        }

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Monospaced", Font.BOLD, 14));
        list.setBackground(new Color(240, 240, 240));

        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton btnClose = new JButton("Cerrar");
        btnClose.addActionListener(e -> dispose());
        add(btnClose, BorderLayout.SOUTH);
    }
}