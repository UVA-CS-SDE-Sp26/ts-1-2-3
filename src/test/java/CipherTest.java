//package java;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    @Test
    void encryptAndDecrypt_workCorrectly() {
        Cipher cipher = new Cipher(new Scanner("ABCD WXYZ"));

        // Encrypt
        String encrypted = cipher.encrypt("ABCD");
        assertEquals("WXYZ", encrypted);

        // Decrypt
        String decrypted = cipher.decrypt(encrypted);
        assertEquals("ABCD", decrypted);
    }

    @Test
    void ciphering_returnsCorrectOutput() {
        Cipher cipher = new Cipher(new Scanner("AB XY"));

        String result = cipher.ciphering("AB");

        assertTrue(result.contains("Original:\nAB"));
        assertTrue(result.contains("Ciphered:\nXY"));
        assertTrue(result.contains("Deciphered:\nAB"));
    }

    @Test
    void encrypt_throwsExceptionForInvalidCharacter() {
        Cipher cipher = new Cipher(new Scanner("AB XY"));

        assertThrows(IllegalArgumentException.class, () -> cipher.encrypt("AC"));
    }

    @Test
    void invalidKey_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Cipher(new Scanner("ABC XY"))); // lengths mismatch
    }
}
