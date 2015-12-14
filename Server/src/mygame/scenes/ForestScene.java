package mygame.scenes;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

public class ForestScene {

    private final Node scene;

    public ForestScene(AssetManager assetManager) {
        scene = loadScene(assetManager);
    }

    private Node loadScene(AssetManager assetManager) {
        Node loadedScene = (Node) assetManager.loadModel("Scenes/Forest.j3o");

        final Spatial buggy = assetManager.loadModel("Models/Buggy/Buggy.j3o");
        buggy.setName("Buggy");
        buggy.setUserData("broadcast", true);
        buggy.setUserData("id", "someid");
        buggy.setLocalTranslation(4, 1, 0);
        buggy.addControl(new AbstractControl() {

            private float sum = 0f;

            @Override
            protected void controlUpdate(float tpf) {
                sum += tpf;
                Vector3f d = new Vector3f((float) Math.cos(sum) * 10, 0, (float) Math.sin(sum) * 10);
                buggy.lookAt(buggy.getLocalTranslation(), d);
                buggy.setLocalTranslation(d);
            }

            @Override
            protected void controlRender(RenderManager rm, ViewPort vp) {
            }

        });
        loadedScene.attachChild(buggy);

        return loadedScene;
    }

    public Node getScene() {
        return scene;
    }

}
