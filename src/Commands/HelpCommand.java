package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CommandsManager;
import Utility.ConsoleManager;

import java.util.LinkedList;
import java.util.List;

/**
 * help command
 */
public class HelpCommand implements Command{
    private CommandsManager commandsManager = new CommandsManager();
    private ConsoleManager consoleManager;

    public HelpCommand(){
        this.consoleManager = new ConsoleManager();
    }
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "command gives a summary of all other commands";
    }

    /**
     * command gives a summary of all other commands
     * @param argument
     * @exception WrongAmountCommandsException
     */
    @Override
    public void execute(String argument){
        try {
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            List<Command> listCommands = commandsManager.getCommandsList();
            consoleManager.println(getName() + ": " + getDescription());
            for (Command commands : listCommands) {
                consoleManager.println(commands.getName() + ": " + commands.getDescription());
            }
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
