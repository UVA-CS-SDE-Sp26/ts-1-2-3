public class CommandLineInterface {
    public UserRequest parseArguments(String[] args) {
        if (args.length == 0) {
            return new UserRequest(true, null, null);
        }
        if (args.length == 1) {
            return new UserRequest(true, args[0], null);
        }
        if (args.length == 2) {
            return new UserRequest(true, args[0], args[1]);
        }

        throw new IllegalArgumentException("Too many arguments");
    }
}
