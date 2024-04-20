package Controler.RequestToServer;

import Controler.Handlers.Handler;
import com.google.common.primitives.Bytes;
import org.apache.commons.lang3.SerializationUtils; // погуглите данную библиотеку
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

// response - ответ
// request - запрос
public class UDPClient {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private final DatagramChannel client;
    private final InetSocketAddress addr; //IP-шка нашего компа

    private final Logger logger;

    public UDPClient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port); //адрес сервера в специальном формате для сокета
        this.client = DatagramChannel.open().bind(null).connect(addr); // открываем канал датаграммы и
        // связываем его с нашим локальным адресом, значение которого присваивается автоматически
        // поскольку мы поставили в bind null, после этого подключаем сокет к нужному серверу канала
        this.client.configureBlocking(false); //сделали поток неблокирующим
        logger.info("DatagramChannel подключен к " + addr);
    }

    public Response sendAndReceiveCommand(Handler request) throws IOException {
        var data = SerializationUtils.serialize(request); // мы сделали ГЛУБОКОЕ клонирование объекта
        // для его сериализации
        var responseBytes = sendAndReceiveData(data);

        Response response = SerializationUtils.deserialize(responseBytes);
        logger.info("Получен ответ от сервера:  " + response);
        return response;
    }

    private void sendData(byte[] data) throws IOException {

        byte[][] ret = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];
        // здесь определяется размер наших "чанков" (это наши порционные ячейки данных для отправки
        // на сервер; Это может быть полезно, если данные слишком велики для отправки целиком
        // или если сеть не поддерживает передачу больших объемов данных за один раз) равное отношению
        // размеру передаваемых данных к количеству чанков с округлением в большую сторону

        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }
        // цикл выше заполняет чанки байтами нашего объекта
        logger.info("Отправляется " + ret.length + " чанков...");

        for(int i = 0; i < ret.length; i++) {
            var chunk = ret[i];
            if (i == ret.length - 1) {
                var lastChunk = Bytes.concat(chunk, new byte[]{1});
                client.send(ByteBuffer.wrap(lastChunk), addr); //обертываем в буффер наш чанк
                logger.info("Последний чанк размером " + lastChunk.length + " отправлен на сервер.");
            } else {
                var answer = Bytes.concat(chunk, new byte[]{0});
                client.send(ByteBuffer.wrap(answer), addr);
                logger.info("Чанк размером " + answer.length + " отправлен на сервер.");
            }
        }
        // После того как все чанки будут сформированы, они отправляются на сервер.
        // Последний чанк отличается тем, что к нему добавляется дополнительный байт
        // (в данном случае значение 1), который указывает серверу, что это последний чанк.
        logger.info("Отправка данных завершена.");
    }

    private byte[] receiveData() throws IOException {
        var received = false;
        var result = new byte[0];

        while(!received) { // до тех пор, пока не получит последний чанк, отмеченный специальным байтом

            var data = receiveData(PACKET_SIZE);
            logger.info("Получено \"" + new String(data) + "\"");
            logger.info("Последний байт: " + data[data.length - 1]);

            if (data[data.length - 1] == 1) {
                received = true;
                logger.info("Получение данных окончено");
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }

        return result;
    }

    private byte[] receiveData(int bufferSize) throws IOException {
        var buffer = ByteBuffer.allocate(bufferSize);
        SocketAddress address = null;
        while(address == null) {
            address = client.receive(buffer); //Если дейтаграмма доступна немедленно
            // то дейтаграмма копируется в указанный байтовый буфер и возвращается ее исходный адрес.
            // Если этот канал находится в неблокирующем режиме и дейтаграмма недоступна немедленно,
            // то этот метод немедленно возвращает результат null.
        }
        return buffer.array();
    }
    // отправляем байты объекты и получаем обратно байты объекта-ответа
    private byte[] sendAndReceiveData(byte[] data) throws IOException {
        sendData(data);
        return receiveData();
    }
}