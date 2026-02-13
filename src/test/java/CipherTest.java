//package java;

import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    private File createTempKeyFile(String content) throws IOException {
        File tempFile = File.createTempFile("key", ".txt");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }

        return tempFile;
    }

    @Test
    void decrypt_worksCorrectly() throws Exception {
        File keyFile = createTempKeyFile("ABCD\nWXYZ");
        Cipher cipher = new Cipher("", keyFile.getAbsolutePath());

        String decrypted = cipher.decrypt("WZYXWYZ");
        assertEquals("ADCBACD", decrypted);
    }

    @Test
    void decrypt_preservesCharactersNotInKey() throws Exception {
        File keyFile = createTempKeyFile("AB\nXY");
        Cipher cipher = new Cipher("", keyFile.getAbsolutePath());

        String decrypted = cipher.decrypt("XZ!");
        assertEquals("AZ!", decrypted);
    }

    @Test
    void ciphering_returnsCorrectFormattedOutput() throws Exception {
        File keyFile = createTempKeyFile("AB\nXY");
        Cipher cipher = new Cipher("", keyFile.getAbsolutePath());

        String result = cipher.ciphering("XY");

        assertTrue(result.contains("Ciphered:\nXY"));
        assertTrue(result.contains("Deciphered:\nAB"));
    }

    @Test
    void invalidKey_wrongNumberOfLines_throwsException() throws Exception {
        File keyFile = createTempKeyFile("ABC XY");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher("", keyFile.getAbsolutePath()));
    }

    @Test
    void invalidKey_lengthMismatch_throwsException() throws Exception {
        File keyFile = createTempKeyFile("ABC\nXY");

        assertThrows(IllegalArgumentException.class,
                () -> new Cipher("", keyFile.getAbsolutePath()));
    }

    @Test
    void fileNotFound_doesNotCrashConstructor() {
        assertDoesNotThrow(() ->
                new Cipher("", "non_existent_file.txt"));
    }
}
