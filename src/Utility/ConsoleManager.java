package Utility;

import java.util.Scanner;

/**
 * a class that works with the console, is responsible for input and output of information
 */
public class ConsoleManager {
    private Scanner scanner = new Scanner(System.in);

    public Long readLong() {
        if(!ifScannerHasNext()) {
            exit();
        }
        return scanner.nextLong();
    }

    /**
     * a method that returns a scanner to input a string
     * @return scanner.nextLine()
     */
    public String readString(){
        if(!ifScannerHasNext()) {
            exit();
        }
        return scanner.nextLine();
    }
    public Float readFloat(){
        if(!ifScannerHasNext()) {
            exit();
        }
        return scanner.nextFloat();
    }
    public int readInt(){
        if(!ifScannerHasNext()) {
            exit();
        }
        return scanner.nextInt();
    }

    /**
     * method checks for next scannaer input
     * @return scanner.hasNextLine()
     */
    public boolean ifScannerHasNext(){
        return scanner.hasNextLine();
    }

    /**
     * a method that displays information without moving to the next line
     * @param toOut
     */
    public void print(Object toOut){
        System.out.print(toOut);
    }

    /**
     * a method that displays information with a transition to the next line
     * @param toOut
     */
    public void println(Object toOut){
        System.out.println(toOut);
    }

    /**
     * a method that allows you to successfully exit the program
     */
    public void exit(){
        System.exit(0);
    }
}
