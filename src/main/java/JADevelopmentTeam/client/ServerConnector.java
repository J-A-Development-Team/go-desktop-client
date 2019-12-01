package JADevelopmentTeam.client;

import JADevelopmentTeam.common.DataPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLOutput;

class ServerConnector {
    private static ObjectOutputStream os;
    private static ObjectInputStream is;
    private static String host = "localhost";
    private static int port = 4444;
    private static ServerConnector instance;
    private Socket socket;
    private ServerConnector(){
        try {
            socket = new Socket(host,port);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (ConnectException e) {
            System.out.println("Program nie połączył się z serwerem.\n" +
                    "Upewnij się, że serwer został uruchomiony");

        }catch (Exception e){
            System.out.println("Coś nie wyszło");
        }
    }

    static ServerConnector getInstance(){
        if (instance == null) {
            synchronized (ServerConnector.class) {
                if (instance == null) {
                    instance = new ServerConnector();
                }
            }
        }
        return instance;
    }

    public void sendData(DataPackage data) throws IOException {
        os.writeObject(data);
    }
    public DataPackage getData(DataPackage dataPackage) throws IOException, ClassNotFoundException {
        return (DataPackage) is.readObject();
    }
}
