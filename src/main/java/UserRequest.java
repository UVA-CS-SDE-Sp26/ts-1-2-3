public class UserInterface {
    private final boolean listFiles;
    private final String fileNumber;
    private final String cipherKey;

    public UserInterface(boolean listFiles, String fileNumber, String cipherKey) {
        this.listFiles = listFiles;
        this.fileNumber = fileNumber;
        this.cipherKey = cipherKey;
    }

    public boolean isListFiles() {
        return listFiles;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public String getCipherKey() {
        return cipherKey;
    }
}
