import java.util.ArrayList;

public class ProgramControl{

    private CommandLineInterface cli;
    private FileHandler fileHandler;

    public ProgramControl(CommandLineInterface cli, FileHandler fileHandler) {
        this.cli = cli;
        this.fileHandler = fileHandler;
    }

    public void run(String[] args) {

        //pull from the CLI to get what argument we need
        UserRequest req = cli.parseArguments(args);

        //displsay list of files
        ArrayList<String> files = fileHandler.findFiles();

        //dif null output all files
        if (req.getFileNumber() == null) {

            for (int i = 0; i < files.size(); i++) {
                String num = String.format("%02d", i + 1);
                System.out.println(num + " " + files.get(i));
            }
            return;
        }


        int index = Integer.parseInt(req.getFileNumber()) - 1;

        if (index < 0 || index >= files.size()) {
            System.out.println("Error: selection out of range");
            return;
        }

        String filename = files.get(index);

        //read file using file handler
        String contents = fileHandler.readFile(filename);

        //print the content
        System.out.println(contents);
    }
}
