package Utility;

import java.util.Scanner;

public class ConsoleManager {
    private Scanner scanner = new Scanner(System.in);

    public Long readLong() {

        return scanner.nextLong();
    }
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
    public boolean ifScannerHasNext(){
        return scanner.hasNextLine();
    }

    public void print(Object toOut){
        System.out.print(toOut);
    }
    public void println(Object toOut){
        System.out.println(toOut);
    }
    public void exit(){
        System.exit(0);
    }
}
