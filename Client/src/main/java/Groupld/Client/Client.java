package Groupld.Client;

import Groupld.Client.Terminal.ColorOutput;
import Groupld.Client.Terminal.TerminalInput;
import Groupld.Client.Terminal.TerminalManager;
import Groupld.Client.Terminal.TerminalOutput;
import Groupld.Controler.ChannelClientServerUtil.Checker;
import Groupld.Controler.Exceptions.IllegalAddressException;
import Groupld.Controler.Exceptions.InvalidInputException;
import Groupld.Controler.Exceptions.NoConnectionException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class Client {
    public static boolean script = false;
    public static TerminalOutput terminalOutput;
    public static TerminalInput terminalInput;
    public static TerminalManager terminalManager;
    public static ClientSendToServer clientSendToServer;
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
                clientSendToServer = new ClientSendToServer(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, terminalOutput);
                CommandRequestManager commandRequestManager = new CommandRequestManager();
                CommandResponseFromServerManager commandResponseFromServerManager = new CommandResponseFromServerManager();
                terminalManager = new TerminalManager(commandRequestManager, terminalInput, terminalOutput, commandResponseFromServerManager);
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


