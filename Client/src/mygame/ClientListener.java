package mygame;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Node;
import java.util.concurrent.Callable;

public class ClientListener implements MessageListener<Client>{
    
    private final ClientMain app;

    ClientListener(ClientMain app) {
        this.app = app;
    }

    public void messageReceived(Client source, Message m) {
        System.out.print("Message received yo \n");
        if(m instanceof WorldLoadMessage){
            WorldLoadMessage loadMessage = (WorldLoadMessage) m;
            System.out.print(loadMessage.getSceneName() + "\n");
            app.enqueue(new Callable<Node>() {

                public Node call() throws Exception {
                    TerrainScene scene = new TerrainScene();
                    Node terrainScene = scene.createAScene(app);
                    app.getRootNode().attachChild(terrainScene);
                    return terrainScene;
                }
                
            });
        }
    }

}
