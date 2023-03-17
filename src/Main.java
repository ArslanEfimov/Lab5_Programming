import Commands.CommandsProcessing;
import Exceptions.WrongValuesException;
import ParceFile.FileReader;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class Main {
    public static void main(String[] args) throws WrongValuesException{
        FileReader fileReader = new FileReader();
        try {
            fileReader.readCollection(args[0]);
        }catch(AccessDeniedException ex){
            System.out.println("Не удалось прочитать файл, так как приложение не может получить доступ к этому файлу. " +
                    "Свяжитесь с владельцем для выдачи прав доступа");
        }
        catch (IOException ex){
            System.out.println("Непредвиденная ошибка во время чтения файла, загрузка приостановлена");
        }
        try {
            CommandsProcessing commandsProcessing = new CommandsProcessing();
            commandsProcessing.switchCommands();
        } catch (NullPointerException e) {
            System.out.println("В коллекции содержится null элемент");
        }
    }

}