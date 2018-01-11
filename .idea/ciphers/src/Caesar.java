import java.util.*;

public class Caesar {
    public static String encrypt (String msg, int key){
        String encrypted = "";
        for(int i = 0; i < msg.length(); i++) {
            int c = msg.charAt(i);
            if (Character.isUpperCase(c)) {
                c = c + (key % 26);
                if (c > 'Z')
                    c = c - 26;
            }
            else if (Character.isLowerCase(c)) {
                c = c + (key % 26);
                if (c > 'z')
                    c = c - 26;
            }
            encrypted += (char) c;
        }
        return encrypted;
    }
    public static String decrypt(String msg, int key)
    {
        String decrypted = "";
        for(int i = 0; i < msg.length(); i++)
        {
            int c = msg.charAt(i);
            if (Character.isUpperCase(c))
            {
                c = c - (key % 26);
                if (c < 'A')
                    c = c + 26;
		 	}
            else if (Character.isLowerCase(c))
            {
                c = c - (key % 26);
                if (c < 'a')
                    c = c + 26;
            }
            decrypted += (char) c;
        }
        return decrypted;
    }
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a message");
        String a = scanner.nextLine();
        System.out.println("Please enter a key");
        int b = scanner.nextInt();
        System.out.println("Encrypted: " + encrypt(a, b));
        System.out.println("Decrypted: " + decrypt(encrypt(a, b), b));
    }
}
