import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CommandLineInterfaceTest {

    @Test
    void noArguments_listsFiles() {
        CommandLineInterface cli = new CommandLineInterface();
        UserRequest req = cli.parseArguments(new String[]{});

        assertTrue(req.isListFiles());
        assertNull(req.getFileNumber());
        assertNull(req.getCipherKey());
    }

    @Test
    void oneArgument_requestsFile() {
        CommandLineInterface cli = new CommandLineInterface();
        UserRequest req = cli.parseArguments(new String[]{"01"});

        assertFalse(req.isListFiles());
        assertEquals("01", req.getFileNumber());
    }

    @Test
    void twoArguments_requestsFileWithKey() {
        CommandLineInterface cli = new CommandLineInterface();
        UserRequest req = cli.parseArguments(new String[]{"01", "key.txt"});

        assertEquals("01", req.getFileNumber());
        assertEquals("key.txt", req.getCipherKey());
    }

    @Test
    void tooManyArguments_throwsException() {
        CommandLineInterface cli = new CommandLineInterface();

        assertThrows(
                IllegalArgumentException.class,
                () -> cli.parseArguments(new String[]{"1", "2", "3"})
        );
    }
}
