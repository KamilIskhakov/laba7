package Groupld.Client.Terminal;

import Groupld.Client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Scanner;

public class TerminalInput {
    private InputStream inputStream;
    ArrayDeque<String[]> scriptBox = new ArrayDeque<>();
    private final TerminalOutput outputManager;

    public TerminalInput(InputStream inputStream, TerminalOutput outputManager) {
        this.inputStream = inputStream;
        this.outputManager = outputManager;
    }

    public String[] readTerminal() {
        try {
            Scanner scanners = new Scanner(inputStream);
            String readline = scanners.nextLine();
            if (readline.contains(" ")) {
                return readline.split(" ");
            } else {
                return new String[]{readline,null};

            }
        } catch (Exception e) {
            outputManager.println("Строка по какой-то причине не была прочитана");
            outputManager.printlnWriteCommand();
        }
        return new String[0];
    }


    public void readScript(String scriptPath) {
        Client.script = true;
        File scriptFile = new File(scriptPath);
        Scanner scriptScanner = null;
        try {
            scriptScanner = new Scanner(scriptFile);
        } catch (FileNotFoundException e) {
            Client.script = false;
            System.out.println("Файл не найден");
        }
        if (scriptScanner != null) {
            while (scriptScanner.hasNextLine()) {
                String readLine = scriptScanner.nextLine();
                if (readLine.contains(" ")) {
                    scriptBox.add(readLine.split(" "));
                } else {
                    scriptBox.add(new String[]{readLine,""});
                }
            }
        }
    }


}
