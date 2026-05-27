package TetrisMain.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String FILE_NAME = "records.txt";

    // Guarda el récord en el archivo
    public static void saveRecord(String username, int score) {
        String sanitizedUser = username != null ? username.replace(",", "_") : "Player";
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(sanitizedUser + "," + score);
        } catch (IOException e) {
            System.err.println("Error al guardar el récord: " + e.getMessage());
        }
    }

    // Lee los récords (útil para mostrar un top en el futuro)
    public static List<String> getRecords() {
        List<String> records = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return records;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer los récords: " + e.getMessage());
        }
        return records;
    }
}