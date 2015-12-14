package mygame.states;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Server;
import com.jme3.scene.Node;
import java.util.List;
import java.util.stream.Collectors;
import mygame.NodeInfo;
import mygame.SceneInfo;
import mygame.messages.server.SceneUpdateMessage;

public class UpdateBroadcaster extends AbstractAppState {

    private final Server server;
    private final Node rootNode;
    private final float updatesPerSecond;
    private float timeSinceLastUpdate = 0f;

    public UpdateBroadcaster(Server server, Node rootNode, float updatesPerSecond) {
        this.server = server;
        this.rootNode = rootNode;
        this.updatesPerSecond = updatesPerSecond;
    }

    private float wait = 0f;

    @Override
    public void update(float tpf) {
        wait += tpf;
        if (wait < 15) {
            return;
        }
        timeSinceLastUpdate += tpf;
        if (timeSinceLastUpdate < updatesPerSecond / 60) {
            return;
        }
        System.out.println("Broadcasting");
        timeSinceLastUpdate = 0f;
        List<NodeInfo> nodes = ((Node) rootNode.getChild("Forest")).getChildren()
                .stream()
                .filter(s -> s.getUserData("broadcast") != null)
                .map(s -> {
                    NodeInfo info = new NodeInfo();
                    info.setId(s.getUserData("id"));
                    info.setTranslation(s.getLocalTranslation());
                    return info;
                })
                .collect(Collectors.toList());
        final SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setNodes(nodes);
        server.broadcast(new SceneUpdateMessage(sceneInfo));
    }

}
