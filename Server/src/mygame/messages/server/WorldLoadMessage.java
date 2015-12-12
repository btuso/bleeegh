package mygame.messages.server;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import mygame.SceneInfo;

@Serializable
public class WorldLoadMessage extends AbstractMessage {

    private String sceneName;
    private SceneInfo sceneInfo;

    public WorldLoadMessage() {
        setReliable(true);
    }

    public WorldLoadMessage(String sceneName, SceneInfo info) {
        setReliable(true);
        this.sceneName = sceneName;
        this.sceneInfo = info;
    }

    public String getSceneName() {
        return sceneName;
    }

    public SceneInfo getSceneInfo() {
        return sceneInfo;
    }

}
