package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.ConsoleManager;

public class ExitCommand implements Command{
    private ConsoleManager consoleManager;
    public ExitCommand(){
        consoleManager = new ConsoleManager();
    }
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Terminates the application (without saving collection)";
    }

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            consoleManager.println("Good bye, my dear)");
            consoleManager.exit();
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
