/**
 * Commmand Line Utility
 */

import java.io.FileNotFoundException;
import java.util.*;

public class TopSecret {
    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
        CommandLineInterface cli = new CommandLineInterface();
        FileHandler fh = new FileHandler();
        ProgramControl pc = new ProgramControl(cli, fh);
        String cipheredMessage = pc.run(args);
        if (!cipheredMessage.isEmpty()) {
            UserRequest ur = cli.parseArguments(args);
            String cipherFileName = "ciphers/key.txt";
            if (ur.getCipherKey() != null) {
                cipherFileName = "ciphers/" + ur.getCipherKey();
            }
//            String cipherKey = fh.readFile(cipherFileName);
            Cipher cipher = new Cipher(cipheredMessage, cipherFileName);
            cipher.ciphering(cipheredMessage);
        }
    }
}
