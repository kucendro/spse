import java.util.Scanner;

public class Delastelle {
    private static final int GRID_SIZE = 5;
    private static char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    private static String key = "PHQGIUMEAYLNOFDXKRCVSTZWB";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadej text k zašifrování: ");
        String text = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        initializeGrid();
        String encrypted = encrypt(text);
        System.out.println("Zašifrovaný text:  " + encrypted);
        scanner.close();
    }

    private static void initializeGrid() {
        int index = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = key.charAt(index++);
            }
        }
    }

    private static String encrypt(String text) {
        int[] coords = new int[2 * text.length()];
        int c = 0;

        // Získání pozic v gridu
        for (char ch : text.toCharArray()) {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    if (grid[i][j] == ch) {
                        coords[c++] = i;
                        coords[c++] = j;
                        break;
                    }
                }
            }
        }

        // Frakční proces
        StringBuilder fractioned = new StringBuilder();
        for (int i = 0; i < coords.length / 2; i++) {
            fractioned.append(coords[i]);
        }
        for (int i = coords.length / 2; i < coords.length; i++) {
            fractioned.append(coords[i]);
        }

        // Šifrování
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < fractioned.length(); i += 2) {
            int x = Character.getNumericValue(fractioned.charAt(i));
            int y = Character.getNumericValue(fractioned.charAt(i + 1));
            encrypted.append(grid[x][y]);
        }

        return encrypted.toString();
    }
}

