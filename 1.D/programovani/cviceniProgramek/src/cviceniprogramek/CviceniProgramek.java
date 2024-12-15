/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cviceniprogramek;
import java.util.Scanner;

/**
 *
 * @author Ondřej Kučera
 * 
 - vytvoř  dvojrozměrné pole typu float
 - vytvoř funkci createArray  s hlavičkou string text
 - zeptáme se uživatele na počet řádků
 - vkládáme jednotlivé řádky
 - sady čísel zařadíme  do řetězce
 - rozřežeme funkcí String split
 - převedeme na float, zajistíme výjimkou
 - opakujeme rekurzí
 -funkce print array, vypíšeme pod sebou řádky pole
 * 
 */



public class CviceniProgramek {
    

    public static void main(String[] args) {
        System.out.print("|    | | ----- -----    |\n|    | |   |   |        |\n |  |  |   |   |---     |\n  ||   |   |   |     \\  |\n  ||   |   |   -----  \\/\n\n");
        float[][] array = createArray("Zadej počet řádků: ");
        printArray(array);
    }

    
    public static float[][] createArray(String text) {
        
        Scanner skenrik = new Scanner(System.in);
        System.out.print(text);
        int pocetRadkuPoli = skenrik.nextInt();
        

        float[][] newArray = new float[pocetRadkuPoli][];

        for (int i = 0; i < pocetRadkuPoli; i++) {
            try {
                System.out.print("Zadej hodnoty " + (i + 1) + ". řádku: ");
                skenrik.nextLine();
                
                String vstup = skenrik.nextLine();
                String[] znaky = vstup.split("\\W" );
                newArray[i] = new float[znaky.length];

                
                for (int j = 0; j < znaky.length; j++) {
                    newArray[i][j] = Float.parseFloat(znaky[j]);
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Zadal jsi neplatný vstup. Dělěj to opravit!!!");
               
                i--;
            }
            System.out.print("\n");
        }

        return newArray;
    }
    
    

    public static void printArray(float[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.print("\n\n@2023 All rights reserved.\nVšechna Vámi zadaná data nyní náleží majiteli programu Ondřeji Kučerovi.\nZadáním dat jste se vzdal jakéhokoliv nároku na soudní spor.\n\n ");
    }
    
}