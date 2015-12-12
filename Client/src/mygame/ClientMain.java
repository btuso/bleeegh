package mygame;

import mygame.messages.server.WorldLoadMessage;
import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;
import java.io.IOException;

public class ClientMain extends SimpleApplication {

    private static final ClientMain app = new ClientMain();
    private Client myClient;

    public static void main(String[] args) {
        app.start(JmeContext.Type.Display); // standard display type
    }

    @Override
    public void simpleInitApp() {
        try {
            registerMessages();
            startClientConnection();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void registerMessages() {
        Serializer.registerClass(WorldLoadMessage.class);
        Serializer.registerClass(SceneInfo.class);
        Serializer.registerClass(NodeInfo.class);
    }

    private void startClientConnection() throws IOException {
        myClient = Network.connectToServer("localhost", 6143);
        myClient.addMessageListener(new ClientListener(app));
        myClient.start();
    }

    @Override
    public void destroy() {
        myClient.close();
        super.destroy();
    }
}
