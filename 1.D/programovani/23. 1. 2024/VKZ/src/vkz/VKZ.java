/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vkz;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
/**
 *
 * @author Ondřej Kučera @2024
 */

public class VKZ extends JFrame {

        public VKZ() {
            ImageIcon ikona = new ImageIcon(VKZ.class.getResource("/src/ico/iko.ico"));
        setIconImage(ikona.getImage());
        }
        
    private static boolean vlcik;
    private static boolean koza;
    private static boolean zeli;
    private static boolean breh;
      
        
    public static void main(String[] args) { 
        
        inicializaceHry();
        zobrazStavHry();

        while (!hraSkoncila()) {
            provedKrok();
            zobrazStavHry();
        }

        if (vlcik && koza && zeli && !breh) {
            JOptionPane.showMessageDialog(null, "Gratuluji, převezl(a) jsi všechny v pořádku na druhý břeh!", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
            provedKrok();
            zobrazStavHry();
        
        } else {
            JOptionPane.showMessageDialog(null, "Bohužel, vlk sežral kozu & koza sežrala zelí. Hra končí.", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
            System.exit(0);   
        }
    }
    
    
    private static void inicializaceHry() {
        vlcik = true;
        koza = true;
        zeli = true;
        breh = true;
    }

    private static void zobrazStavHry() {
        JOptionPane.showMessageDialog(null, "Poloha vlka: " + (vlcik ? "na" : "na druhém") + " břehu" + "\n" + "Poloha kozy: " + (koza ? "na" : "na druhém") + " břehu" + "\n" + "Poloha zelí: " + (zeli ? "na" : "na druhém") + " břehu" + "\n" + "Poloha převozníka: " + (breh ? "první" : "druhý") + " břeh", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
    }

    private static void provedKrok() {

        String[] moznosti = { "Nic", "Převést vlka" , "Převést kozu" , "Převést zelí" , "Ukončit hru" };
        int postava = JOptionPane.showOptionDialog(null, "Vítej ve hře!\n \nDokážeš převést všechny členy\nna druhý břeh?\n \nVyber si jednu z postav\na zkus všechny bez ujmy převést.\n \nHodně štěstí ;)", "(V)lk, (K)oza a (Z)elí", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"), moznosti, null);
            
        if (postava == 1) {
            vlcik = prepravVlka();
        } else if (postava == 2) {
            koza = prepravKozu();
        } else if (postava == 3) {
            zeli = prepravZeli();
        } else if (postava == 4) {
            System.exit(0);
        } else if (postava == 0) {
            
        }

        breh = !breh;
    }

    private static boolean prepravVlka() {
        if (vlcik && breh) {
            return false;
        } else if (!vlcik && !breh) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nemůžeš nechat vlka a kozu samy na jednom břehu!", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
            return vlcik;
        }
    }

    private static boolean prepravKozu() {
        if (koza && breh) {
            return false;
        } else if (!koza && !breh) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nemůžeš nechat kozu a zelí samy na jednom břehu!", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
            return koza;
        }
    }

    private static boolean prepravZeli() {
        if (zeli && breh) {
            return false;
        } else if (!zeli && !breh) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Nemůžeš nechat zelí a kozu samy na jednom břehu!", "(V)lk, (K)oza a (Z)elí", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("/Users/mpmp/Documents/SPŠE /Programování/23. 1. 2024/VKZ/src/ico/ikona.png"));
            return zeli;
        }
    }
    

    private static boolean hraSkoncila() {
        return !((vlcik && koza) || (koza && zeli));
    }
}

    
    


