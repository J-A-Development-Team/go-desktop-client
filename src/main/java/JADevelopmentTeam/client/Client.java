package JADevelopmentTeam.client;

public class Client {
    private static ServerConnector  serverConnector;

    public static void main(String[] args) {
        serverConnector = ServerConnector.getInstance();
    }
}


