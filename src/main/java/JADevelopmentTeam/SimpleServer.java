package JADevelopmentTeam;

import JADevelopmentTeam.common.DataPackage;
import JADevelopmentTeam.common.Stone;
import JADevelopmentTeam.server.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class SimpleServer {
    private static SimpleServer instance = null;
    private ServerSocket serverSocket = null;
    private ExecutorService pool;


    private SimpleServer() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("port assigned");
        } catch (IOException ex) {
            System.out.println("Could not listen on port 4444");
            System.exit(-1);
        }
    }

    static SimpleServer getInstance() {
        if (instance == null) {
            instance = new SimpleServer();
        }
        return instance;
    }

    Player[] initializePlayers() {
        Player[] players = new Player[2];
        players[0] = connectPlayer(pool);
        if (players[0] == null) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }
        return players;
    }

    private Player connectPlayer(ExecutorService pool) {
        Socket socket;
        System.out.println("Oczekuję na klienta");
        try {
            socket = serverSocket.accept();
            Player player = new Player(socket);
            System.out.println("Connected First Player!");
            return player;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleServer simpleServer = SimpleServer.getInstance();
        Player[] players = simpleServer.initializePlayers();
        Stone[][] stones= new Stone[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stones[j][i] = new Stone(j,i,false);
            }
        }
        while (true){
            DataPackage dataPackage = null;
            try {
                players[0].receive();
                dataPackage = players[0].getDataPackage();
            } catch (NullPointerException | ClassNotFoundException e){
                e.printStackTrace();
            }
            if (dataPackage!=null){
                Stone stone = (Stone) dataPackage.getData();
                System.out.println("Odebrałem "+ stone.getXCoordinate()+ " "+ stone.getYCoordinate());
                stones[stone.getXCoordinate()][stone.getYCoordinate()] = new Stone(stone.getXCoordinate(),stone.getYCoordinate());
            }else {
                System.out.println("nie dziala");
            }
            players[0].send(new DataPackage(stones,DataPackage.Info.StoneTable));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.println(stones[j][i].exist()+" "+j+" "+i);
                }
            }
        }
    }
}
