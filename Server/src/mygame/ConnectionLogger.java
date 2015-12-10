package mygame;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class ConnectionLogger implements ConnectionListener{

    public void connectionAdded(Server server, HostedConnection conn) {
        System.out.print("new connection " + conn.getAddress() + "\n");
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
        System.out.print("he's dead jim \n");
    }
    
}
