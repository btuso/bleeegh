package mygame;

import com.jme3.network.serializing.Serializable;
import java.util.List;

@Serializable(id = 0)
public class SceneInfo {

    private List<NodeInfo> nodes;

    public SceneInfo() {
    }

    public List<NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeInfo> nodes) {
        this.nodes = nodes;
    }
}
