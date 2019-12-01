package JADevelopmentTeam.client;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

//Make sure that server works
public class ServerConnectorTest {
    @Test
    public void testGetSingleton() {
        ServerConnector connector = ServerConnector.getInstance();
        assertNotNull(connector);
    }

    @Test
    public void testIsSingleton() {
        ServerConnector c1 = ServerConnector.getInstance();
        ServerConnector c2 = ServerConnector.getInstance();
        Assert.assertSame(c1, c2);
    }

}
