package mygame.messages.server;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import mygame.SceneInfo;

@Serializable
public class SceneUpdateMessage extends AbstractMessage {

    private SceneInfo sceneInfo;

    public SceneUpdateMessage() {
        setReliable(false);
    }

    public SceneUpdateMessage(SceneInfo sceneInfo) {
        setReliable(false);
        this.sceneInfo = sceneInfo;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(SceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

}
