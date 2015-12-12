package mygame;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;
import mygame.messages.server.WorldLoadMessage;

public class ConnectionSceneLoader implements ConnectionListener {

    private final Node scene;

    public ConnectionSceneLoader(Node scene) {
        this.scene = scene;
    }

    public void connectionAdded(Server server, HostedConnection conn) {
        SceneInfo sceneInfo = new SceneInfo();

        List<NodeInfo> nodeInfos = new ArrayList();
        for (Spatial child : scene.getChildren()) {
            if (child.getUserData("broadcast") != null) {
                nodeInfos.add(mapSpatialToNodeInfo(child));
            }
        }

        sceneInfo.setNodes(nodeInfos);

        conn.send(new WorldLoadMessage(scene.getName(), sceneInfo));
    }

    private NodeInfo mapSpatialToNodeInfo(Spatial s) {
        NodeInfo nodeInfo = new NodeInfo();

        nodeInfo.setName(s.getName());
        nodeInfo.setTranslation(s.getLocalTranslation());
        nodeInfo.setId((String) s.getUserData("id"));

        return nodeInfo;
    }

    public void connectionRemoved(Server server, HostedConnection conn) {
    }

}
