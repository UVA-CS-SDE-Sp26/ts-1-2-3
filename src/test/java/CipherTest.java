//package java;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    @Test
    void encryptAndDecrypt_workCorrectly() {
        Cipher cipher = new Cipher("WZYXWYZ", "ABCD\nWXYZ");

        // Decrypt
        String decrypted = cipher.decrypt("WZYXWYZ");
        assertEquals("ADCBACD", decrypted);
    }

    @Test
    void ciphering_returnsCorrectOutput() {
        Cipher cipher = new Cipher("XY", "AB\nXY");

        String result = cipher.ciphering("XY");

        assertTrue(result.contains("Ciphered:\nXY"));
        assertTrue(result.contains("Deciphered:\nAB"));
    }

    @Test
    void encrypt_throwsExceptionForInvalidCharacter() {
        Cipher cipher = new Cipher("AC", "AB\nXY");

        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt("AC"));
    }

    @Test
    void invalidKey_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Cipher("ASDO", "ABC\nXY")); // lengths mismatch
    }
}
