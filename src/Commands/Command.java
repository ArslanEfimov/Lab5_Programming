package Commands;

/**
 * base interface for all commands
 */
public interface Command {

    String getName();

    String getDescription();

    void execute(String argument);

}
