package mygame;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable(id = 2)
public class NodeInfo {

    private String modelName;
    private Vector3f translation;
    private String id;

    public NodeInfo() {
    }

    public void setName(String name) {
        this.modelName = name;
    }

    public void setTranslation(Vector3f localTranslation) {
        this.translation = localTranslation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public String getId() {
        return id;
    }
}
