import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRequestTest {
    @Test
    void constructor_setsAllFields() {
        UserRequest req = new UserRequest(false, "02", "altkey.txt");

        assertFalse(req.isListFiles());
        assertEquals("02", req.getFileNumber());
        assertEquals("altkey.txt", req.getCipherKey());
    }

    @Test
    void listFilesRequest_hasNoFileNumber() {
        UserRequest req = new UserRequest(true, null, null);

        assertTrue(req.isListFiles());
        assertNull(req.getFileNumber());
        assertNull(req.getCipherKey());
    }
}
