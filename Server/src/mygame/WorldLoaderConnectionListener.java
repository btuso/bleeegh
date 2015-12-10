package mygame;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class WorldLoaderConnectionListener implements ConnectionListener{

    public void connectionAdded(Server server, HostedConnection conn) {
        conn.send(new WorldLoadMessage("InitialScene"));
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
    }

}
