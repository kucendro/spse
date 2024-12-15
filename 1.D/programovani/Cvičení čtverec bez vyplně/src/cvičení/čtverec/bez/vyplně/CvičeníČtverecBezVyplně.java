/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cvičení.čtverec.bez.vyplně;

import java.util.Scanner;

/**
 *
 * @author mpmp
 */
public class CvičeníČtverecBezVyplně {


    
    
    static String[][] drawRectangle () {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadejte šířku obrazce. (BODY)");
        int sirka = sc.nextInt();
        
        System.out.println("Zadejte výšku obrazce. (BODY)");
        int vyska = sc.nextInt();
        
        String znak = " ";
        
        System.out.println("Vyberte znak ohraničení.");
        String ohraniceni = sc.next();
        
        System.out.println("\nObrazec:\n");
        
        String[][] poleRect = new String[vyska][sirka];
        
        for (int i = 0; i < poleRect.length; i++) {
            
                for (int j = 0; j < poleRect[i].length; j++) {
                poleRect[i][j] = znak;
               }
            
                    for (int j = 0; j < poleRect.length; j++) {
                
                    poleRect[i][0] = ohraniceni;
                    poleRect[i][poleRect[i].length - 1] = ohraniceni;
                    poleRect[0][i] = ohraniceni;
                    poleRect[poleRect.length - 1][j] = ohraniceni;
                }
                        }
        
        
        for (int i = 0; i < poleRect.length; i++) {
            
            String docasna = "";
            
            for (int j = 0; j < poleRect.length; j++) {
                
                docasna+= poleRect[i][j];
            }
            System.out.println("\u001B[32m" + docasna + "\u001B[0m");
        }
        return poleRect;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        drawRectangle();

    }
    
}
