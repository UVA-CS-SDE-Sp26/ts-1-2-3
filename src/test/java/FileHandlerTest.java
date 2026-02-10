import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.io.*;

class FileHandlerTest {
    @Test
    void noDataFolder() {
        FileHandler fileHandler = new  FileHandler();

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(false);

        ArrayList<String> result = fileHandler.findFiles(mockFolder);

        assertEquals(new ArrayList<String>(), result, "Should return empty list");
        verify(mockFolder).exists();
        verifyNoMoreInteractions(mockFolder);
    }

    @Test
    void noFilesInDataFolder() {
        FileHandler fileHandler = new  FileHandler();

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.getName()).thenReturn("data");
        when(mockFolder.listFiles()).thenReturn(new File[0]);

        ArrayList<String> result = fileHandler.findFiles(mockFolder);

        assertEquals(new ArrayList<String>(), result, "Should return empty list");
        verify(mockFolder).exists();
        verify(mockFolder).isDirectory();
        verify(mockFolder).getName();
        verify(mockFolder).listFiles();
        verifyNoMoreInteractions(mockFolder);
    }

    @Test
    void testFolderWithOneTxtFile() {
        FileHandler fileHandler = new FileHandler();

        File mockFile = mock(File.class);
        when(mockFile.isFile()).thenReturn(true);
        when(mockFile.getName()).thenReturn("file1.txt");

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.getName()).thenReturn("data");

        when(mockFolder.listFiles()).thenReturn(new File[] {mockFile});

        ArrayList<String> result = fileHandler.findFiles(mockFolder);

        assertEquals(1, result.size(), "List returned should have 1 file");
        assertEquals("file1.txt", result.get(0));

        verify(mockFolder).listFiles();
        verify(mockFile, times(2)).getName();
    }

    @Test
    void testFolderWithTwoTxtFiles() {
        FileHandler fileHandler = new FileHandler();

        File mockFile1 = mock(File.class);
        when(mockFile1.isFile()).thenReturn(true);
        when(mockFile1.getName()).thenReturn("file1.txt");

        File mockFile2 = mock(File.class);
        when(mockFile2.isFile()).thenReturn(true);
        when(mockFile2.getName()).thenReturn("file2.txt");

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.getName()).thenReturn("data");

        when(mockFolder.listFiles()).thenReturn(new File[] {mockFile1, mockFile2});

        ArrayList<String> result = fileHandler.findFiles(mockFolder);

        assertEquals(2, result.size(), "List returned should have 2 items");
        assertEquals("file1.txt", result.get(0));
        assertEquals("file2.txt", result.get(1));

        verify(mockFolder).listFiles();
        verify(mockFile1, times(2)).getName();
        verify(mockFile2, times(2)).getName();
    }

    @Test
    void specifiedFileDoesNotExist() {
        FileHandler fileHandler = new FileHandler();
        Scanner mockScanner = mock(Scanner.class);

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.getName()).thenReturn("data");

        File mockFile = mock(File.class);
        when(mockFile.getName()).thenReturn("file1.txt");
        when(mockFolder.listFiles()).thenReturn(new File[] {mockFile});

        String result = fileHandler.readFile(mockFolder, "nonExistingFile", mockScanner);

        assertEquals("", result);
        verify(mockFolder).exists();
        verify(mockFolder).isDirectory();
        verify(mockFolder).getName();
        verify(mockFolder).listFiles();
        verifyNoMoreInteractions(mockFolder);
    }

    @Test
    void specifiedFileExists() {
        FileHandler handler = new FileHandler();
        Scanner mockScanner = mock(Scanner.class);

        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        when(mockFolder.getName()).thenReturn("data");

        File mockFile = mock(File.class);
        when(mockFile.getName()).thenReturn("file2.txt");
        when(mockFolder.listFiles()).thenReturn(new File[] {mockFile});

        when(mockScanner.hasNextLine()).thenReturn(true, true, false);
        when(mockScanner.nextLine()).thenReturn("abc", "123");

        String result = handler.readFile(mockFolder, "file2.txt", mockScanner);

        assertEquals("abc\n123\n", result);
        verify(mockFolder).exists();
        verify(mockFolder).isDirectory();
        verify(mockFolder).getName();
        verify(mockFolder).listFiles();
        verifyNoMoreInteractions(mockFolder);
        verify(mockScanner, times(3)).hasNextLine();
        verify(mockScanner, times(3)).hasNextLine();
        verify(mockScanner, times(2)).nextLine();
        verifyNoMoreInteractions(mockFolder);
    }
}