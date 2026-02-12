import java.util.*;
import java.io.*;

public class Cipher {
    protected HashMap<Character, Character> decryptor;

    public Cipher(String word, String key) {
        decryptor = new HashMap<>();
        setup(key);
    }
    private void setup(String key) {
        String[] lines = key.split("\\n");
        if (lines.length != 2) {
            throw new IllegalArgumentException("Invalid key, there must be 2 lines. ");
        }
        String original = lines[0];
        String ciphered = lines[1];
        if (original.length() != ciphered.length()) {
            throw new IllegalArgumentException("This is not a valid key.");
        }
        for (int i = 0; i < original.length(); i++) {
            decryptor.put(ciphered.charAt(i), original.charAt(i));
        }
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
        String deciphered = decrypt(text);
        String result = "Ciphered:\n" + text + "\nDeciphered:\n" + deciphered;
        System.out.println(result);
        return result;
    }
}
