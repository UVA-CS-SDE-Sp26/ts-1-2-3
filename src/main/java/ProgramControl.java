import java.util.ArrayList;

public class ProgramControl{

    private CommandLineInterface cli;
    private FileHandler fileHandler;

    public ProgramControl(CommandLineInterface cli, FileHandler fileHandler) {
        this.cli = cli;
        this.fileHandler = fileHandler;
    }

    public String run(String[] args) {

        UserRequest req = cli.parseArguments(args);
        ArrayList<String> files = fileHandler.findFiles();

        // If no argument, return file list
        if (req.getFileNumber() == null) {

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < files.size(); i++) {
                String num = String.format("%02d", i + 1);
                result.append(num)
                        .append(" ")
                        .append(files.get(i))
                        .append("\n");
            }

            System.out.println(result.toString());
            return "";
        }

        int index = Integer.parseInt(req.getFileNumber()) - 1;

        if (index < 0 || index >= files.size()) {
            return "Error: selection out of range";
        }

        String filename = files.get(index);
        return fileHandler.readFile(filename);
    }

}
