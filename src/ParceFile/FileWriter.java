package ParceFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {
public void writerInFile(String path) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(path);
    writer.write("<?xml version=\"1.0\"?>");
    writer.write("\n");
    writer.write("<organizations>");

    writer.write("\n");
    writer.write("</organizations>");
    writer.flush();
    writer.close();
}


}
