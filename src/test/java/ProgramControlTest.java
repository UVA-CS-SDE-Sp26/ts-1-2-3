import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.ArrayList;

import org.junit.jupiter.api.*;

public class ProgramControlTest {

    CommandLineInterface cli = mock(CommandLineInterface.class);
    FileHandler fh = mock(FileHandler.class);
    ProgramControl pc = new ProgramControl(cli, fh);

    ByteArrayOutputStream out;

    @BeforeEach
    void setup() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    private ArrayList<String> files(String... names) {
        return new ArrayList<>(java.util.List.of(names));
    }

    @Test
    void printsFileListWhenNull() {
        UserRequest req = mock(UserRequest.class);
        when(cli.parseArguments(new String[]{})).thenReturn(req);
        when(req.getFileNumber()).thenReturn(null);
        when(fh.findFiles()).thenReturn(files("a.txt","b.txt"));

        pc.run(new String[]{});

        assertTrue(out.toString().contains("01 a.txt"));
        verify(fh, never()).readFile(any());
    }

    @Test
    void printsErrorWhenOutOfRange() {
        UserRequest req = mock(UserRequest.class);
        when(cli.parseArguments(new String[]{"5"})).thenReturn(req);
        when(req.getFileNumber()).thenReturn("5");
        when(fh.findFiles()).thenReturn(files("a.txt"));

        pc.run(new String[]{"5"});

        assertTrue(out.toString().contains("Error"));
        verify(fh, never()).readFile(any());
    }

    @Test
    void printsFileContentsWhenValid() {
        UserRequest req = mock(UserRequest.class);
        when(cli.parseArguments(new String[]{"2"})).thenReturn(req);
        when(req.getFileNumber()).thenReturn("2");
        when(fh.findFiles()).thenReturn(files("a.txt","b.txt"));
        when(fh.readFile("b.txt")).thenReturn("DATA");

        pc.run(new String[]{"2"});

        assertTrue(out.toString().contains("DATA"));
        verify(fh).readFile("b.txt");
    }
}
