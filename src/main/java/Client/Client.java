package Client;

import Controler.CommandRequestManager;
import Client.Terminal.TerminalInput;
import Client.Terminal.TerminalManager;
import Client.Terminal.TerminalOutputManager;

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
    public static TerminalOutputManager terminalOutputManager;
    public static TerminalInput terminalInput;
    public static TerminalManager terminalManager;
    public static ClientToSend clientToSend;
    private static final int TIMEOUT = 100;
    private static final int BUFFER_SIZE = 3048;
    private static final int RECONNECTION_ATTEMPTS = 5;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    public static void main(String[] args) {
        terminalOutputManager = new TerminalOutputManager(System.out);
        terminalInput = new TerminalInput(System.in, terminalOutputManager);
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try (DatagramChannel client = DatagramChannel.open()) {
                InetSocketAddress serverAddress = Checker.checkAddress(args[0], args[1]);
                client.bind(null).configureBlocking(false);
                clientToSend = new ClientToSend(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, terminalOutputManager);
                CommandRequestManager commandRequestManager = new CommandRequestManager();
                terminalManager = new TerminalManager(commandRequestManager, terminalInput, terminalOutputManager);
                terminalManager.start();
            } catch (InvalidInputException | NoConnectionException | IllegalAddressException e) {
                terminalOutputManager.printlnImportantColorMessage(e.getMessage(), Color.RED);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                terminalOutputManager.printlnImportantColorMessage("error during connection:", Color.RED);
                e.printStackTrace();
            }
        } else {
            terminalOutputManager.printlnImportantColorMessage("please enter a server hostname and port as a command "
                    + "line arguments", Color.RED);
        }

    }

}


