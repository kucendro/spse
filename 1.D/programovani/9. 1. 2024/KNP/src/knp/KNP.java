/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package knp;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Font;

/**
 *                  ZADÁNÍ
 * 
 * - budeme mít třídu KNP
 * - v Mainu bude jeden řádek, vyvolá funkci
 * - vyřešíme ve funkci
 * - 1. vytěžství hráče
 * 
 * @author Ondřej Kučera
 */


public class KNP {

    
    public static void main(String[] args) { // hlavní funkce, volá všechny ostatní, postupně, pokud někde problém, stopne program
        vyber();
                Font můjFont = new Font("Calibri", Font.BOLD, 20);
    }
    
    
    public static void vyber() { // funkce výběru pro tah hráčem
        Random nahodny = new Random();

        while (true) {
            
            String[] moznosti = { "Kámen" , "Nůžky" , "Papír" };
            int hracVolba = JOptionPane.showOptionDialog(null, "Vítej ve hře proti Macu!\nVyber si jednu z možností!\nStačí pouze kliknout.", "(K)ámen(N)ůžky(P)apír", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/9. 1. 2024/KNP/src/ico/ikona.png"), moznosti, null);

            int pocitacVolbaCislo = nahodny.nextInt(3); // 0 pro kámen, 1 pro nůžky, 2 pro papír

            String pocitacVolba;
            switch (pocitacVolbaCislo) {
                case 0:
                    pocitacVolba = "kámen";
                    break;
                case 1:
                    pocitacVolba = "nůžky";
                    break;
                case 2:
                    pocitacVolba = "papír";
                    break;
                default:
                    pocitacVolba = "";
            }

            JOptionPane.showMessageDialog(null, "Počítač zvolil: " + pocitacVolba);

           
            
           if ((hracVolba) == (pocitacVolbaCislo)) {
                JOptionPane.showMessageDialog(null, "Remíza");
            } else if (((hracVolba == 0) && (pocitacVolbaCislo == 1))
                    || ((hracVolba == 1) && (pocitacVolbaCislo == 2))
                    || ((hracVolba == 2) && (pocitacVolbaCislo == 0))) {
                JOptionPane.showMessageDialog(null, "Vyhrál jsi!");
            } else {
                JOptionPane.showMessageDialog(null, "Mac vyhrál!   ");
            }
            konec();
        }

    }
    
    public static void konec() {
         
        while (true) {
            
            String[] dveMoznosti = { "Pokračovat" , "Ukončit" };
            int volba = JOptionPane.showOptionDialog(null, "Přejete si pokračovat a přijmout výzvu?", "(K)ámen(N)ůžky(P)apír", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, dveMoznosti, null);
            
                if (volba == 1) {
                    System.out.println("Hra ukončena.");
                    System.exit(0);
                } else {
                vyber();
                }
            
         }

    }
    

}    




    
