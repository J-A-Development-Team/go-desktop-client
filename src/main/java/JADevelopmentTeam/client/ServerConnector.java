package JADevelopmentTeam.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerConnector {
    ObjectOutputStream os;
    ObjectInputStream is;
    private static String host = "localhost";
    private static int port = 58907;
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
    };

    public static ServerConnector getInstance(){
        if (instance == null) {
            synchronized (ServerConnector.class) {
                if (instance == null) {
                    instance = new ServerConnector();
                }
            }
        }
        return instance;
    }

}
