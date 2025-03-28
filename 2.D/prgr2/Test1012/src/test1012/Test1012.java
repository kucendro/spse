/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test1012;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ondřej Kučera
 * Datum 10. 12. 2024
 * Omlouvám se za hrubé vyjadřívání v kódu, je cílem pro našeho učitele!
 */

public class Test1012 {

    public static void main(String[] args) {
        StringBuilder userSteps = new StringBuilder("Kroky uživatele:\n");

        JFrame frame = new JFrame("Okínko pro úchyla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));

        String[] dialogOptions = {"Input Dialog", "Confirm", "Option", "Message"};
        JComboBox<String> dialogComboBox = new JComboBox<>(dialogOptions);
        panel.add(new JLabel("Vyberte typ dialogu úchyle:"));
        panel.add(dialogComboBox);

        JButton showDialogButton = new JButton("Zobrazit dialog");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDialog = (String) dialogComboBox.getSelectedItem();

                switch (selectedDialog) {
                    case "Input Dialog":
                        String input = JOptionPane.showInputDialog(frame, 
                                "Zadejte text úchyle:", 
                                "Input Dialog", 
                                JOptionPane.PLAIN_MESSAGE);
                        if (input != null) {
                            userSteps.append("Input Dialog: Zadali jste - ").append(input).append("\n");
                            JOptionPane.showMessageDialog(frame, 
                                    "Zadali jste: " + input, 
                                    "Výsledek", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;

                    case "Confirm":
                        int confirm = JOptionPane.showConfirmDialog(frame, 
                                "Chcete pokračovat?", 
                                "Confirm Dialog", 
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            userSteps.append("Confirm Dialog: Zvoleno ANO\n");
                            JOptionPane.showMessageDialog(frame, 
                                    "Zvolili jste ANO.", 
                                    "Výsledek", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            userSteps.append("Confirm Dialog: Zvoleno NE\n");
                            JOptionPane.showMessageDialog(frame, 
                                    "Zvolili jste NE.", 
                                    "Výsledek", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;

                    case "Option":
                        String[] options = {"4", "3", "2", "1"};
                        int choice = JOptionPane.showOptionDialog(frame, 
                                "Vyberte možnost:", 
                                "Option Dialog", 
                                JOptionPane.DEFAULT_OPTION, 
                                JOptionPane.QUESTION_MESSAGE, 
                                null, 
                                options, 
                                options[0]);
                        if (choice != -1) {
                            userSteps.append("Option Dialog: Zvoleno - ").append(options[choice]).append("\n");
                            JOptionPane.showMessageDialog(frame, 
                                    "Zvolili jste: " + options[choice], 
                                    "Výsledek", 
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;

                    case "Message":
                        JOptionPane.showMessageDialog(frame, 
                                userSteps.toString(), 
                                "Postup úchyla", 
                                JOptionPane.INFORMATION_MESSAGE);
                        break;

                    default:
                        JOptionPane.showMessageDialog(frame, 
                                "Neznámý dialog.", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

        frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(showDialogButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}