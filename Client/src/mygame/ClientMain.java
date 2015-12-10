package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;

public class ClientMain extends SimpleApplication {

    private static final ClientMain app = new ClientMain();
    private Client myClient;

    public static void main(String[] args) {
        app.start(JmeContext.Type.Display); // standard display type
    }

    @Override
    public void simpleInitApp() {
        try {

            Serializer.registerClass(WorldLoadMessage.class);

            myClient = Network.connectToServer("localhost", 6143);
            myClient.addMessageListener(new ClientListener(app));

            myClient.start();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        myClient.close();
        super.destroy();
    }
}
