package mygame.messages.server;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import mygame.SceneInfo;

@Serializable
public class WorldLoadMessage extends AbstractMessage {

    private SceneInfo sceneInfo;

    public WorldLoadMessage() {
        setReliable(true);
    }

    public WorldLoadMessage(SceneInfo info) {
        setReliable(true);
        this.sceneInfo = info;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

}
