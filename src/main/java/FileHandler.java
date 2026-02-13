import java.util.*;
import java.io.*;

// Sources:
// https://www.geeksforgeeks.org/java/java-program-to-traverse-in-a-directory/
// https://www.w3schools.com/java/java_files_read.asp

// There are 2 methods actually used in FileHandler class but cannot test with mockito unless mock objects are passed in.
// So each method has an overloaded method used only for mockito tests
// So all lines inside overloaded methods are tested but not the ones in the other original since those are not testable with mockito
public class FileHandler {
    // Method to use for actual program
    public ArrayList<String> findFiles(){
        ArrayList<String> fileNameList = new ArrayList<>();
        File dataFolder = new File("data");
        if (!dataFolder.exists() || !dataFolder.isDirectory() || !dataFolder.getName().equals("data")) {
            System.out.println("No folder named 'data' exists, returned empty list");
        }
        else {
            for (File file : dataFolder.listFiles()){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    fileNameList.add(file.getName());
                }
            }
        }
        return fileNameList;
    }

    // Same as previous method, just overloaded so can test with Mockito, should not be used for actual program
    public ArrayList<String> findFiles(File dataFolder){
        ArrayList<String> fileNameList = new ArrayList<>();
        if (!dataFolder.exists() || !dataFolder.isDirectory() || !dataFolder.getName().equals("data")) {
            System.out.println("No folder named 'data' exists, returned empty list");
        }
        else {
            for (File file : dataFolder.listFiles()){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    fileNameList.add(file.getName());
                }
            }
        }
        return fileNameList;
    }

    // Method to use for actual program
    public String readFile(String specifiedFileName){
        File specifiedFile = null;
        String specifiedFileContent = "";
        File dataFolder = new File("data");

        if (!dataFolder.exists() || !dataFolder.isDirectory() || !dataFolder.getName().equals("data")) {
            System.out.println("No folder named 'data' exists,  returned empty string");
            return specifiedFileContent;
        }
        else {
            for (File file : dataFolder.listFiles()){
                if (file.getName().equals(specifiedFileName)){
                    specifiedFile = file;
                }
            }

            if (specifiedFile == null){
                System.out.println("No file named '" + specifiedFileName + "' exists, returned empty string");
                return specifiedFileContent;
            }
        }

        try (Scanner scanner = new Scanner(specifiedFile)){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                specifiedFileContent += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("Error, returned empty string");
            return specifiedFileContent;
        }

        return specifiedFileContent;
    }

    // Same as previous method, just overloaded so can test with Mockito, should not be used for actual program
    public String readFile(File dataFolder, String specifiedFileName, Scanner scanner){
        File specifiedFile = null;
        String specifiedFileContent = "";

        if (!dataFolder.exists() || !dataFolder.isDirectory() || !dataFolder.getName().equals("data")) {
            System.out.println("No folder named 'data' exists,  returned empty string");
            return specifiedFileContent;
        }
        else {
            for (File file : dataFolder.listFiles()){
                if (file.getName().equals(specifiedFileName)){
                    specifiedFile = file;
                }
            }

            if (specifiedFile == null){
                System.out.println("No file named '" + specifiedFileName + "' exists, returned empty string");
                return specifiedFileContent;
            }
        }

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                specifiedFileContent += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("Error, returned empty string");
            return specifiedFileContent;
        }

        return specifiedFileContent;
    }
}
