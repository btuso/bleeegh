package mygame;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class WorldLoadMessage extends AbstractMessage{

    private String sceneName;
    
    public WorldLoadMessage() {
        setReliable(true);
    }

    public WorldLoadMessage(String sceneName) {
        setReliable(true);
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

}
