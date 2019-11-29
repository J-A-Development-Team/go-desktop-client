package JADevelopmentTeam.client;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;

//Make sure that server works
public class ServerConnectorTest {
    @BeforeClass
    public static void server() {
        new MockServerClient("localhost", 4444);

    }

    @Test
    public void testGetSingleton() {
        ServerConnector connector = ServerConnector.getInstance();
        assertNotNull(connector);
    }

    @Test
    public void testIsSingleton(){
        ServerConnector c1 = ServerConnector.getInstance();
        ServerConnector c2 = ServerConnector.getInstance();
        Assert.assertSame(c1,c2);
    }
    //Server should work
    @Test
    public void createObjectOutputStreamConnection(){

        ServerConnector.getInstance();
        Assert.assertNotNull(ServerConnector.getObjectOutputStream());
    }
    //Server should work
    @Test
    public void createObjectInputStreamConnection(){
        ServerConnector.getInstance();
        Assert.assertNotNull(ServerConnector.getObjectInputStream());
    }



}
