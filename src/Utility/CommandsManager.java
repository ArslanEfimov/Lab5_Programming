package Utility;

import Commands.*;
import Exceptions.WrongValuesException;
import ParceFile.FileManagerReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandsManager {
    String[] s;
    private HashMap<String, Command> commandsMap;
    private List<Command> commandsListForHelp;
    private ConsoleManager consoleManager;
    public CommandsManager(CollectionManager collectionManager, FileManagerReader reader,List<String> saveFileNameForExecute){
        this.consoleManager = new ConsoleManager();
        commandsMap = new HashMap<>();
        commandsMap.put("clear", new ClearCommand(collectionManager));
        commandsMap.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandsMap.put("help", new HelpCommand());
        commandsMap.put("info", new InfoCommand(collectionManager));
        commandsMap.put("remove_first", new RemoveFirstCommand(collectionManager));
        commandsMap.put("show", new ShowCommand(collectionManager));
        commandsMap.put("update_by_id", new UpdateIdCommand(collectionManager));
        commandsMap.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commandsMap.put("filter_by_annual_turnover", new FilterAnnualTurnoverCommand(collectionManager));
        commandsMap.put("count_greater_than_official_address", new CountGreaterThanOfficialAddressCommand(collectionManager));
        commandsMap.put("print_descending", new PrintDescendingCommand(collectionManager));
        commandsMap.put("add", new AddCommand(collectionManager));
        commandsMap.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandsMap.put("execute_script", new ExecuteScriptCommand(collectionManager,saveFileNameForExecute,reader));
        commandsMap.put("save", new SaveCommand(collectionManager, reader));
        commandsMap.put("exit", new ExitCommand());

    }
    public CommandsManager(){
        commandsListForHelp = new ArrayList<>();
        commandsListForHelp.add(new ClearCommand());
        commandsListForHelp.add(new RemoveByIdCommand());
        commandsListForHelp.add(new InfoCommand());
        commandsListForHelp.add(new RemoveFirstCommand());
        commandsListForHelp.add(new ShowCommand());
        commandsListForHelp.add(new UpdateIdCommand());
        commandsListForHelp.add(new RemoveGreaterCommand());
        commandsListForHelp.add(new FilterAnnualTurnoverCommand());
        commandsListForHelp.add(new CountGreaterThanOfficialAddressCommand());
        commandsListForHelp.add(new PrintDescendingCommand());
        commandsListForHelp.add(new AddCommand());
        commandsListForHelp.add(new AddIfMaxCommand());
        commandsListForHelp.add(new ExecuteScriptCommand());
        commandsListForHelp.add(new SaveCommand());
        commandsListForHelp.add(new ExitCommand());
    }


    public void commandManager(){
        consoleManager.print("enter command: ");
        while (consoleManager.ifScannerHasNext()) {
           try {
                s = consoleManager.readString().trim().split(" ");
                if(s.length>2) throw new WrongValuesException("invalid value format entered");
                if(s.length==2 && s[0].equals(s[1])) throw new WrongValuesException("invalid value format entered");
                if (commandsMap.get(s[0]) == null) {
                    consoleManager.println("no such command");
                    consoleManager.print("enter command: ");
                } else {
                    commandsMap.get(s[0]).execute(s[s.length - 1]);
                    consoleManager.print("enter command: ");
                }
           }catch(WrongValuesException ex){
               consoleManager.println(ex.getMessage());
               consoleManager.print("enter command: ");
            }
        }
    }

    public List<Command> getCommandsList(){
        return commandsListForHelp;
    }
    public HashMap<String, Command> getCommandsMap(CollectionManager collectionManager,List<String> saveFileNameForExecute,FileManagerReader reader){
        commandsMap = new HashMap<>();
        commandsMap.put("clear", new ClearCommand(collectionManager));
        commandsMap.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandsMap.put("help", new HelpCommand());
        commandsMap.put("info", new InfoCommand(collectionManager));
        commandsMap.put("remove_first", new RemoveFirstCommand(collectionManager));
        commandsMap.put("show", new ShowCommand(collectionManager));
        commandsMap.put("update_by_id", new UpdateIdCommand(collectionManager));
        commandsMap.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commandsMap.put("filter_by_annual_turnover", new FilterAnnualTurnoverCommand(collectionManager));
        commandsMap.put("count_greater_than_official_address", new CountGreaterThanOfficialAddressCommand(collectionManager));
        commandsMap.put("print_descending", new PrintDescendingCommand(collectionManager));
        commandsMap.put("add", new AddCommand(collectionManager));
        commandsMap.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commandsMap.put("execute_script", new ExecuteScriptCommand(collectionManager,saveFileNameForExecute,reader));
        commandsMap.put("save", new SaveCommand(collectionManager, reader));
        commandsMap.put("exit", new ExitCommand());
        return commandsMap;
    }

}