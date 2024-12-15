
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 * 1. 10. 2024
 * @author Ondřej Kučera
 */
public class TestJFrame {
    
  static void testJFrame(){
      JFrame frame = new JFrame("JFrame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 500);
      frame.setLocationRelativeTo(null);
      frame.setLayout(new BorderLayout());
      
//      JPanel pan = new JPanel();
//      frame.add(pan, BorderLayout.CENTER);
              
      JButton button = new JButton("OK");
      JLabel label = new JLabel("Zdarec, toto je testovací text!");
      JLabel labelmeni = new JLabel("0");

      button.addActionListener(new ActionListener() {
           @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "Kliknul jsi!");
            System.out.println("Kliknul jsi!");
  }
});
frame.add(button);
button.setSize(30, 30);
frame.add(label, BorderLayout.CENTER);
frame.setVisible(true);

  

  }
  
    public static void main(String[] args) {
        System.out.println("Test JFrame");
        testJFrame();
    }
}
