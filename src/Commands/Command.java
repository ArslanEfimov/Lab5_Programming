package Commands;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Exceptions.OrganizationNotFoundException;
import Exceptions.WrongValuesException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {

    String getName();

    String getDescription();

    void execute(String argument) throws MustNotBeEmptyException, WrongValuesException, NotInDeclaredLimitsException, OrganizationNotFoundException, FileNotFoundException, IOException;

}
