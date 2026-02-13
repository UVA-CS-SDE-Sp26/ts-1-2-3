/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        CommandLineInterface cli = new CommandLineInterface();
        ProgramControl pc = new ProgramControl(cli, fileHandler);
        pc.run(args);

    }
}
