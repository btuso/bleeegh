package mygame;

import com.jme3.network.serializing.Serializable;
import java.util.List;

@Serializable(id = 0)
public class SceneInfo {

    private List<NodeInfo> nodes;
    private String name;

    public SceneInfo() {
    }

    public SceneInfo(String name) {
        this.name = name;
    }

    public List<NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeInfo> nodes) {
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
