package src;

import javax.swing.*;
import java.awt.*;

public class SplitPaneExample {
    public static void main(String[] args) {
        // Création du frame
        JFrame frame = new JFrame("Exemple de JSplitPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Création des panneaux gauche et droit
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Panneau Gauche"));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(new JLabel("Panneau Droit"));

        // Création du JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400); // Position du séparateur initial

        // Ajout du JSplitPane au frame
        frame.getContentPane().add(splitPane);

        // Rendre le frame visible
        frame.setVisible(true);
    }
}
