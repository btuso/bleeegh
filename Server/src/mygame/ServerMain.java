package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;

public class ServerMain extends SimpleApplication {

    private Server myServer;

    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless); // headless type for servers!
    }

    @Override
    public void simpleInitApp() {
        try {

            rootNode.attachChild(new TerrainScene().createAScene(assetManager));


            Serializer.registerClass(WorldLoadMessage.class);
            
            myServer = Network.createServer(6143);
            
            myServer.addConnectionListener(new ConnectionLogger());
            myServer.addConnectionListener(new WorldLoaderConnectionListener());
            myServer.start();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        myServer.close();
        super.destroy();
    }
}
