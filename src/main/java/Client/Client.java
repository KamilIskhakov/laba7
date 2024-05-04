package Client;

import Client.Terminal.ColorOutput;
import Client.Terminal.TerminalInput;
import Client.Terminal.TerminalManager;
import Client.Terminal.TerminalOutput;

import Controler.RequestToServer.Checker;
import Controler.Exceptions.IllegalAddressException;
import Controler.Exceptions.InvalidInputException;
import Controler.Exceptions.NoConnectionException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class Client {
    public static boolean script = false;
    public static TerminalOutput terminalOutput;
    public static TerminalInput terminalInput;
    public static TerminalManager terminalManager;
    public static ClientToSend clientToSend;
    private static final int TIMEOUT = 100;
    private static final int BUFFER_SIZE = 3048;
    private static final int RECONNECTION_ATTEMPTS = 100;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    public static void main(String[] args) {
        terminalOutput = new TerminalOutput(System.out);
        terminalInput = new TerminalInput(System.in, terminalOutput);
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try (DatagramChannel client = DatagramChannel.open()) {
                // имя хоста указывается первой строкой аргумента командной строки, порт – второй
                InetSocketAddress serverAddress = Checker.checkAddress(args[0], args[1]);
                client.bind(null).configureBlocking(false);
                clientToSend = new ClientToSend(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, terminalOutput);
                CommandRequestManager commandRequestManager = new CommandRequestManager();
                CommandResponseManager commandResponseManager = new CommandResponseManager();
                terminalManager = new TerminalManager(commandRequestManager, terminalInput, terminalOutput, commandResponseManager);
                terminalManager.start();
            } catch (InvalidInputException | NoConnectionException | IllegalAddressException e) {
                terminalOutput.printlnImportantColorMessage(e.getMessage(), ColorOutput.RED);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                terminalOutput.printlnImportantColorMessage("error during connection:", ColorOutput.RED);
            }
        } else {
            terminalOutput.printlnImportantColorMessage("please enter a server hostname and port as a command "
                    + "line arguments", ColorOutput.RED);
        }

    }

}


