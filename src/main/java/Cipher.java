import java.util.*;
import java.io.*;

public class Cipher {
    protected HashMap<Character, Character> decryptor;

    public Cipher(String word, String keyFile) throws FileNotFoundException {
        decryptor = new HashMap<>();
        setup(keyFile);
    }
    private void setup(String keyFile) throws FileNotFoundException {
//        String[] lines = key.split("\\n");
        File file = new File(keyFile);
        try {
            Scanner sc = new Scanner(file);
            ArrayList<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            if (lines.size() != 2) {
                throw new IllegalArgumentException("Invalid key, there must be 2 lines. ");
            }
            String original = lines.get(0);
            String ciphered = lines.get(1);
            if (original.length() != ciphered.length()) {
                throw new IllegalArgumentException("This is not a valid key.");
            }
            for (int i = 0; i < original.length(); i++) {
                decryptor.put(ciphered.charAt(i), original.charAt(i));
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }
    public String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!decryptor.containsKey(c)) {
                sb.append(c);
            }
            else {
                sb.append(decryptor.get(c));
            }
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
