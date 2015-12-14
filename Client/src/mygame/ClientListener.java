package mygame;

import mygame.messages.server.WorldLoadMessage;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import mygame.messages.server.SceneUpdateMessage;

public class ClientListener implements MessageListener<Client> {

    private final ClientMain app;

    ClientListener(ClientMain app) {
        this.app = app;
    }

    @Override
    public void messageReceived(Client source, Message m) {
        System.out.print("Message received yo \n");
        if (m instanceof WorldLoadMessage) {
            final WorldLoadMessage loadMessage = (WorldLoadMessage) m;
            for (NodeInfo node : loadMessage.getSceneInfo().getNodes()) {
                System.out.println(node.getId());
                System.out.println(node.getModelName());
                System.out.println(node.getTranslation());
            }
            app.enqueue(() -> {
                Node loadedScene = (Node) app.getAssetManager().loadModel("Scenes/" + loadMessage.getSceneInfo().getName() + ".j3o");

                for (NodeInfo node : loadMessage.getSceneInfo().getNodes()) {
                    Spatial buggy = app.getAssetManager().loadModel("Models/" + node.getModelName() + "/" + node.getModelName() + ".j3o");
                    buggy.setName(node.getId());
                    buggy.setUserData("id", node.getId());
                    buggy.setLocalTranslation(node.getTranslation());
                    loadedScene.attachChild(buggy);
                }

                app.getRootNode().attachChild(loadedScene);
                return loadedScene;
            });
        } else if (m instanceof SceneUpdateMessage) {
            final SceneUpdateMessage updateMessage = (SceneUpdateMessage) m;

            app.enqueue(() -> {
                Node scene = (Node) app.getRootNode().getChild("Forest");
                for (NodeInfo node : updateMessage.getSceneInfo().getNodes()) {
                    System.out.println(node.getTranslation());
                    Node child = (Node) scene.getChild(node.getId());
                    child.setLocalTranslation(node.getTranslation());
                }
                return null;
            });
        }
    }

}
