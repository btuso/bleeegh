package mygame;

import mygame.messages.server.WorldLoadMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.concurrent.Callable;

public class ClientListener implements MessageListener<Client> {

    private final ClientMain app;

    ClientListener(ClientMain app) {
        this.app = app;
    }

    public void messageReceived(Client source, Message m) {
        System.out.print("Message received yo \n");
        if (m instanceof WorldLoadMessage) {
            final WorldLoadMessage loadMessage = (WorldLoadMessage) m;
            System.out.print(loadMessage.getSceneName() + "\n");
            for (NodeInfo node : loadMessage.getSceneInfo().getNodes()) {
                System.out.println(node.getId());
                System.out.println(node.getModelName());
                System.out.println(node.getTranslation());
            }
            app.enqueue(new Callable<Node>() {

                @Override
                public Node call() throws Exception {
                    Node loadedScene = (Node) app.getAssetManager().loadModel("Scenes/" + loadMessage.getSceneName() + ".j3o");

                    for (NodeInfo node : loadMessage.getSceneInfo().getNodes()) {
                        Spatial buggy = app.getAssetManager().loadModel("Models/" + node.getModelName() + "/" + node.getModelName() + ".j3o");
                        buggy.setUserData("id", node.getId());
                        buggy.setLocalTranslation(node.getTranslation());
                        loadedScene.attachChild(buggy);
                    }

                    app.getRootNode().attachChild(loadedScene);
                    return loadedScene;
                }
            });
        }
    }

}
