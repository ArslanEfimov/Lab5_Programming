import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Exceptions.OrganizationNotFoundException;
import Exceptions.WrongValuesException;
import ParceFile.FileManagerReader;
import Utility.CollectionManager;
import Utility.CommandsManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws WrongValuesException, IOException,
            MustNotBeEmptyException, NotInDeclaredLimitsException, OrganizationNotFoundException {
        FileManagerReader fileManagerReader = new FileManagerReader(args[0]);
        CollectionManager collectionManager = new CollectionManager(fileManagerReader);
        List<String> saveFileNameForExecute = new ArrayList<>();
        CommandsManager commandsProcessing = new CommandsManager(collectionManager,fileManagerReader,saveFileNameForExecute);
        commandsProcessing.commandManager();

    }

}