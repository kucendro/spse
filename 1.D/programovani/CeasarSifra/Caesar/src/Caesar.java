import java.util.Scanner;



public class Caesar {

    // Metoda pro šifrování textu
    public static String encrypt(String text, int shift) {
        shift = shift % 26 + 26; // Zajistí kladné posunutí
        StringBuilder encrypted = new StringBuilder();
        for (char i : text.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encrypted.append((char) ('A' + (i - 'A' + shift) % 26 ));
                } else {
                    encrypted.append((char) ('a' + (i - 'a' + shift) % 26 ));
                }
            } else {
                encrypted.append(i); // Nealfabetické znaky necháme beze změny
            }
        }
        return encrypted.toString();
    }

    public static void main(String[] args) {

    System.out.println("Chceš šifrovat, nebo dešifrovat? Napiš s pro šifrování, nebo d pro dešifrování.");
    Scanner scanner = new Scanner (System.in);
    String rozhodnuti = scanner.nextLine();
            
        if (rozhodnuti == "s") {
            sifrovat();
            
        } else {
            desifrovat();
        }
    }

    
        static void sifrovat() {
           
        Scanner scanner = new Scanner(System.in);

        // Uživatel zadá text, který chce zašifrovat
        System.out.println("Enter the text to encrypt:");
        String text = scanner.nextLine();

        // Uživatel zadá posun pro šifrování
        System.out.println("Enter the shift key:");
        int shift;
        try {
            shift = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using shift key of 0.");
            shift = 0;
        }

        // Zašifrování zadaného textu
        String encrypted = encrypt(text, shift);
        System.out.println("Encrypted text: " + encrypted);
        
        scanner.close();
    }
}

static void desifrovat() {

System.out.println("");

}
