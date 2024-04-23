package Client;

import Client.Terminal.TerminalOutput;
import Controler.Handlers.Handler;
import Exceptions.NoConnectionException;
import Controler.RequestToServer.*;

import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientToSend {
    private final DatagramChannel client;
    private final InetSocketAddress serverAddress;
    private final int bufferSize;
    private final int timeout;
    private final int reconnectionAttempts;
    private final TerminalOutput outputManager;

    public ClientToSend(DatagramChannel client, InetSocketAddress serverAddress, int timeout, int bufferSize,
                     int reconnectionAttempts, TerminalOutput outputManager) {
        this.client = client;
        this.serverAddress = serverAddress;
        this.timeout = timeout;
        this.bufferSize = bufferSize;
        this.reconnectionAttempts = reconnectionAttempts;
        this.outputManager = outputManager;
    }

    public Object send(Handler request) throws IOException, NoConnectionException, InterruptedException, ClassNotFoundException {
        byte[] bytesSending = Serializer.serialize(request);
        ByteBuffer wrapperSending = ByteBuffer.wrap(bytesSending); //обертываем в буффер наш чанк
        for (int attempt = 1; attempt <= reconnectionAttempts; attempt++) {
            if (client.send(wrapperSending, serverAddress) == bytesSending.length) {
                break;//короче, если все отправилось, то выходим из цикла
            } else {
                outputManager.println("Cannot send request to the server. Retrying attempt #"
                        + attempt + " now...");
                if (attempt == reconnectionAttempts) {
                    throw new NoConnectionException();
                }
                Thread.sleep(timeout);
            }
        }
        byte[] bytesReceiving = new byte[bufferSize];
        ByteBuffer wrapperReceiving = ByteBuffer.wrap(bytesReceiving);
        for (int attempt = 1; attempt <= reconnectionAttempts; attempt++) {
            Thread.sleep(timeout);
            if (client.receive(wrapperReceiving) != null) { //Получает дейтаграмму по этому каналу.
                break;
            } else {
                outputManager.printlnImportantColorMessage("Cannot receive response from server. Retrying attempt #"
                        + attempt + " now...", Color.RED);
                if (attempt == reconnectionAttempts) {
                    throw new NoConnectionException();
                }
            }
        }
        return Serializer.deserialize(bytesReceiving);
    }

}
