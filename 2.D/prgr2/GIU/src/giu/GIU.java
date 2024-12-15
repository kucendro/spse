/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package giu;

 /**
  *
  * @author mpmp
  */
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 
 public class GIU {
     
     public static void main(String[] args) {
         // Vytvoření hlavního okna
         JFrame frame = new JFrame("Input Dialog");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(300, 200);
         frame.setLayout(new BorderLayout());

         // Panel pro obsah dialogu
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(3, 1, 5, 5));
         // Přidání zaškrtávacího políčka
         JCheckBox checkBox = new JCheckBox("Select Application");
         panel.add(checkBox);

         // Přidání rozbalovacího menu
         String[] options = {"Malování", "Word", "Excel", "Kalkulačka"};
         JComboBox<String> comboBox = new JComboBox<>(options);
         panel.add(comboBox);

         // Přidání tlačítka
         JButton button = new JButton("EditAndClose");
         button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (checkBox.isSelected()) {
                     String selectedOption = (String) comboBox.getSelectedItem();
                     try {
                         switch (selectedOption) {
                             case "Malování":
                                 Runtime.getRuntime().exec("mspaint");
                                 break;
                             case "Word":
                                 Runtime.getRuntime().exec("winword");
                                 break;
                             case "Excel":
                                 Runtime.getRuntime().exec("excel");
                                 break;
                             case "Kalkulačka":
                                 Runtime.getRuntime().exec("calc");
                                 break;
                         }
                     } catch (Exception ex) {
                         JOptionPane.showMessageDialog(frame,
                                 "Chyba při spouštění aplikace: " + ex.getMessage(),
                                 "Error",
                                 JOptionPane.ERROR_MESSAGE);
                     }
                 } else {
                     JOptionPane.showMessageDialog(frame,
                             "Zaškrtněte 'Select Application' před spuštěním!",
                             "Warning",
                             JOptionPane.WARNING_MESSAGE);
                 }
             }
         });
 
         // Umístění komponent do hlavního okna
         frame.add(panel, BorderLayout.CENTER);
 
         // Panel pro tlačítko
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
         buttonPanel.add(button);
         frame.add(buttonPanel, BorderLayout.SOUTH);
 
         // Zobrazení okna
         frame.setVisible(true);
     }
 }
 
