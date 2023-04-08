package Commands;

import Exceptions.ScriptRecursionException;
import ParceFile.FileManagerReader;
import Utility.CollectionManager;
import Utility.CommandsManager;
import Utility.ConsoleManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * execute_script command
 */
public class ExecuteScriptCommand implements Command {
    private ConsoleManager consoleManager;
    private CollectionManager collectionManager;
    private CommandsManager commandsManager;
    private List<String> saveFileNameForExecute;
    private FileManagerReader reader;

    public ExecuteScriptCommand(CollectionManager collectionManager, List<String> saveFileNameForExecute, FileManagerReader reader) {
        this.consoleManager = new ConsoleManager();
        this.collectionManager = collectionManager;
        this.commandsManager = new CommandsManager();
        this.saveFileNameForExecute = saveFileNameForExecute;
        this.reader = reader;
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

    /**
     * execute script file
     * @param argument
     */
    @Override
    public void execute(String argument){
        File file = new File(argument);
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) throw new NoSuchElementException();
            HashMap<String, Command> executeMap = commandsManager.getCommandsMap(collectionManager,saveFileNameForExecute,reader);
            String[] array = {"", ""};
            String line;
            while (!Objects.equals(array, "exit")) {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                    if(line.isEmpty()){
                        continue;
                    }
                    array = line.split(" ");
                    List<String> arrayWithoutSpaces = new ArrayList<>(Arrays.asList(array));
                    arrayWithoutSpaces.removeIf(element -> element.equals(""));
                    array = arrayWithoutSpaces.toArray(new String[0]);
                    if (array.length > 2) {
                        consoleManager.println("Incorrect number of entered elements");
                        continue;
                    }
                    if (array.length == 2 && array[0].equals(array[1])) {
                        consoleManager.println("invalid value format entered");
                        continue;
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
                    consoleManager.println("");
                }
                if (!scanner.hasNextLine()) {
                    break;
                }
                if(scanner.nextLine().isEmpty()){
                    break;
                }
            }
        } catch (NoSuchElementException ex) {
            consoleManager.println("Boot file is empty!");
        }catch(ScriptRecursionException ex){
            consoleManager.println("recursion detected while executing command");
            saveFileNameForExecute.clear();
        }catch (IOException e) {
            if(Paths.get(argument).toFile().exists()){
                consoleManager.println("no permission to read from file");
            }
            else {
                consoleManager.println("file not found, check file name");
            }
        }finally {
                saveFileNameForExecute.clear();
        }
    }



}
