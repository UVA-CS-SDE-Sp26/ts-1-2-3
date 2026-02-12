import java.util.*;
import java.io.*;

public class Cipher {

    protected static String DEFAULT_FILE_PATH = "ciphers/key.txt";

    protected HashMap<Character, Character> encryptor;
    protected HashMap<Character, Character> decryptor;

    public Cipher(String word) throws FileNotFoundException {
        this(word, DEFAULT_FILE_PATH);
    }

    public Cipher(String word, String filePath) throws FileNotFoundException {
        this(new Scanner(new File(filePath)));
    }

    public Cipher(Scanner keyScanner) {
        encryptor = new HashMap<>();
        decryptor = new HashMap<>();
        setup(keyScanner);
    }

    private void setup(Scanner s) {
        String original = s.next();
        String ciphered = s.next();
        if (original.length() != ciphered.length()) {
            throw new IllegalArgumentException("This is not a valid key.");
        }
        for (int i = 0; i < original.length(); i++) {
            encryptor.put(original.charAt(i), ciphered.charAt(i));
            decryptor.put(ciphered.charAt(i), original.charAt(i));
        }
    }

    public String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!encryptor.containsKey(c)) {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            sb.append(encryptor.get(c));
        }
        return sb.toString();
    }

    public String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!decryptor.containsKey(c)) {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            sb.append(decryptor.get(c));
        }
        return sb.toString();
    }

    public String ciphering(String text) {
        String ciphered = encrypt(text);
        String deciphered = decrypt(ciphered);
        String result = "Original:\n" + text + "\nCiphered:\n" + ciphered + "\nDeciphered:\n" + deciphered;
        System.out.println(result);
        return result;
    }
}
