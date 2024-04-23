package Client.Terminal;

import java.io.IOException;
import java.io.OutputStream;

public class TerminalOutput {
    private final OutputStream outputStream;

    public TerminalOutput(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void println(String string) {
        printlnImportantMessage(string);

    }

    public void printlnColorMessage(String string, ColorOutput color) {
            printlnImportantColorMessage(string, color);

    }

    public void printlnImportantColorMessage(String string, ColorOutput color) {
        printlnImportantMessage(color.colorize(string));
    }
    public void printlnNotCorrectInput() {
        println("Вы ввели некорректные формат данных");
    }

    public void printlnImportantMessage(String string) {
        try {
            outputStream.write(string.getBytes());
            outputStream.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printlnWriteCommand(){
        println("Введите команду:");
    }


    public void print(String string) {
        try {
                outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
