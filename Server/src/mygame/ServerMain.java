package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.scene.Node;
import com.jme3.system.JmeContext;
import java.io.IOException;
import mygame.messages.server.WorldLoadMessage;
import mygame.scenes.ForestScene;

public class ServerMain extends SimpleApplication {

    private Server myServer;

    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless);
    }

    @Override
    public void simpleInitApp() {
        try {
            registerMessages();
            loadWorldState();
            startServer();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void registerMessages() {
        Serializer.registerClass(WorldLoadMessage.class);
        Serializer.registerClass(SceneInfo.class);
        Serializer.registerClass(NodeInfo.class);
    }

    private void loadWorldState() {
        //load scenes!
        ForestScene forest = new ForestScene(assetManager);
        rootNode.attachChild(forest.getScene());

    }

    private void startServer() throws IOException {
        myServer = Network.createServer(6143);
        myServer.addConnectionListener(new ConnectionLogger());
        myServer.addConnectionListener(new ConnectionSceneLoader((Node) rootNode.getChild("Forest")));
        myServer.start();
    }

    @Override
    public void destroy() {
        myServer.close();
        super.destroy();
    }

}
