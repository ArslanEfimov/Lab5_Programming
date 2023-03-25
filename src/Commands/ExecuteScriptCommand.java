package Commands;

import Exceptions.*;
import Utility.CollectionManager;
import Utility.CommandsManager;
import Utility.ConsoleManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExecuteScriptCommand implements Command {
    private ConsoleManager consoleManager;
    private CollectionManager collectionManager;
    private CommandsManager commandsManager;
    private List<String> saveFileNameForExecute;

    public ExecuteScriptCommand(CollectionManager collectionManager,List<String> saveFileNameForExecute) {
        this.consoleManager = new ConsoleManager();
        this.collectionManager = collectionManager;
        this.commandsManager = new CommandsManager();
        this.saveFileNameForExecute = saveFileNameForExecute;
    }

    public ExecuteScriptCommand() {

    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "reads and executes a script from the specified file";
    }

    @Override
    public void execute(String argument) throws IOException, NotInDeclaredLimitsException, OrganizationNotFoundException, MustNotBeEmptyException, WrongValuesException {
        File file = new File(argument);
        try (Scanner scanner = new Scanner(file)) {
            if(collectionManager.getCollectionSize()!=0) {
                if (!scanner.hasNextLine()) throw new NoSuchElementException();
                HashMap<String, Command> executeMap = commandsManager.getCommandsMap(collectionManager,saveFileNameForExecute);
                String[] array = {"", ""};
                String line;
                while (!Objects.equals(array, "exit")) {
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                        if(line.isEmpty()){
                            continue;
                        }
                        array = line.split(" ");
                        if (line.split(" ").length > 2) {
                            consoleManager.println("incorrect values");
                            break;
                        }
                        if (array.length == 2 && array[0].equals(array[1])) {
                            consoleManager.println("invalid value format entered");
                            break;
                        }
                        if (executeMap.get(array[0]) == null) {
                            consoleManager.println("no such command");
                        } else {
                            if (array[0].equals("execute_script")) {
                                if (saveFileNameForExecute.contains(argument)) throw new ScriptRecursionException();
                                else{
                                    saveFileNameForExecute.add(argument);
                                }
                            }

                            if(array.length>1){
                                consoleManager.println(executeMap.get(array[0]).getName() + " " + array[1] + ": ");
                            }
                            else{
                                consoleManager.println(executeMap.get(array[0]).getName() + ": ");
                            }
                            executeMap.get(array[0]).execute(array[array.length - 1]);
                        }
                        consoleManager.println("\n");
                    }
                    if (!scanner.hasNextLine()) {
                        break;
                    }
                    if(scanner.nextLine().isEmpty()){
                        break;
                    }
                }
            }else{
                consoleManager.println("There are no elements in the collection");
            }
            } catch (FileNotFoundException ex) {
                consoleManager.println("Invalid file name specified! Please enter a valid name");
            } catch (NoSuchElementException ex) {
                consoleManager.println("Boot file is empty!");
            }catch(ScriptRecursionException ex){
                consoleManager.println("recursion detected while executing command");
                saveFileNameForExecute.clear();
            }finally {
                saveFileNameForExecute.clear();
        }
        }



}
