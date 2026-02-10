import java.util.*;
import java.io.*;

public class Cipher {

    protected static final String FILE_PATH = "ciphers/key.txt";
    protected HashMap<Character, Character> encryptor, decryptor;

    public Cipher() throws FileNotFoundException {
        this(new Scanner(new File(FILE_PATH)));
        encryptor = new HashMap<>();
        decryptor = new HashMap<>();
    }
    Cipher(Scanner keyScanner) {
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
