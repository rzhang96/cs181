import java.awt.Point;
import java.util.Scanner;

public class playfair {
    private static char[][] cipherTable;
    private static Point[] letters;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter message:");
        String message = in.nextLine();

        System.out.println("Enter cipher:");
        String cipher = in.nextLine();

        System.out.println("Replace J with I? Y or N?");
        String jiSwitch = in.nextLine();

        boolean switchJI = jiSwitch.equalsIgnoreCase("y");

        createTable(cipher, switchJI);

        String result = encryption(rules(message, switchJI));

        System.out.printf("%nEncoded message: %n%s%n", result);
        System.out.printf("%nDecoded message: %n%s%n", decryption(result));
    }

    private static String rules(String msg, boolean switchJI) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        if (switchJI == true) {
            return msg.replace("J", "I");
        } else {
            return msg.replace("Q", "");
        }
    }

    private static void createTable(String cipher, boolean switchJI) {
        cipherTable = new char[5][5];
        letters = new Point[26];

        String alphabet = rules(cipher + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", switchJI);

        int len = alphabet.length();
        for (int i = 0, k = 0; i < len; i++) {
            char letter = alphabet.charAt(i);
            if (letters[letter - 'A'] == null) {
                cipherTable[k / 5][k % 5] = letter;
                letters[letter - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }

    private static String encryption(String msg) {
        StringBuilder sb = new StringBuilder(msg);

        for (int i = 0; i < sb.length(); i += 2) {

            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");

            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return codec(sb, 1);
    }

    private static String decryption(String msg) {
        return codec(new StringBuilder(msg), 4);
    }

    private static String codec(StringBuilder txt, int direction) {
        int len = txt.length();
        for (int i = 0; i < len; i += 2) {
            char a = txt.charAt(i);
            char b = txt.charAt(i + 1);

            int row1 = letters[a - 'A'].y;
            int row2 = letters[b - 'A'].y;
            int col1 = letters[a - 'A'].x;
            int col2 = letters[b - 'A'].x;

            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;

            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;

            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }

            txt.setCharAt(i, cipherTable[row1][col1]);
            txt.setCharAt(i + 1, cipherTable[row2][col2]);
        }
        return txt.toString();
    }
}

