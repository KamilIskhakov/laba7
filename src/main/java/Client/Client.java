package Client;

import Controler.CommandRequestManager;
import Client.Terminal.TerminalInput;
import Client.Terminal.TerminalManager;
import Client.Terminal.TerminalOutput;

import Controler.RequestToServer.Checker;
import Exceptions.IllegalAddressException;
import Exceptions.InvalidInputException;
import Exceptions.NoConnectionException;

import java.awt.*;
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
    private static final int RECONNECTION_ATTEMPTS = 5;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    public static void main(String[] args) {
        terminalOutput = new TerminalOutput(System.out);
        terminalInput = new TerminalInput(System.in, terminalOutput);
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try (DatagramChannel client = DatagramChannel.open()) {
                InetSocketAddress serverAddress = Checker.checkAddress(args[0], args[1]);
                client.bind(null).configureBlocking(false);
                clientToSend = new ClientToSend(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, terminalOutput);
                CommandRequestManager commandRequestManager = new CommandRequestManager();
                terminalManager = new TerminalManager(commandRequestManager, terminalInput, terminalOutput);
                terminalManager.start();
            } catch (InvalidInputException | NoConnectionException | IllegalAddressException e) {
                terminalOutput.printlnImportantColorMessage(e.getMessage(), Color.RED);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                terminalOutput.printlnImportantColorMessage("error during connection:", Color.RED);
                e.printStackTrace();
            }
        } else {
            terminalOutput.printlnImportantColorMessage("please enter a server hostname and port as a command "
                    + "line arguments", Color.RED);
        }

    }

}


