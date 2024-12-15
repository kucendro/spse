/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package znakova_grafika;

/**
 *
 * @author Ondřej Kučera 1.D 2023
 * 
 *      ZADÁNí
 * použijte statické funkce
 * vytvořte obdelník, trojúhelník
 * každá funkce bude Draw_rect...
 * parametry grf, fill
 * lokální string kresba
 * uložit do pole (lokálně) a pak vykreslit
 */
import java.util.Scanner;
public class Znakova_grafika {
    
    
    static String[][] drawRectangle () {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Zadejte šířku obrazce.");
        int sirka = sc.nextInt();
        
        System.out.println("Zadejte výšku obrazce.");
        int vyska = sc.nextInt();
        
        System.out.println("Vyberte znak obrazce.");
        String znak = sc.next();
        
        System.out.println("Vyberte znak mezer.");
        String mezery = sc.next();
        
        System.out.println("Vyberte znak ohraničení.");
        String ohraniceni = sc.next();
        
        String[][] poleRect = new String[vyska][sirka];
        
        for (int i = 0; i < poleRect.length; i++) {
            
            for (int j = 0; j < poleRect[i].length; j++) {
                poleRect[i][j] = znak;
            }
            poleRect[i][0] = ohraniceni;
            poleRect[i][poleRect[i].length - 1] = ohraniceni;
        }
        
        for (int i = 0; i < poleRect.length; i++) {
            
            String docasna = "";
            
            for (int j = 0; j < poleRect.length; j++) {
                
                docasna+= poleRect[i][j];
            }
            
            System.out.println(docasna);
        }
        return poleRect;
    }
    
    

    public static void main (String[] args) {
        drawRectangle();
    }
}

