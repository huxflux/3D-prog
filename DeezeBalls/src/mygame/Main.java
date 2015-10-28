package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        String soccerFilename = ("src\\mygame\\soccerball.blend");
        
        // Testing Coordinate class, it's aMaZzZiNg
        Recording recording = new Recording("src\\mygame\\socket_2013011114_1513.dat");
        System.out.println(recording.numberOfTimestamps());
        System.out.println(recording.numberOfCoordinates());
        for (int i = 0; i < recording.numberOfTimestamps(); i++) {
            System.out.println(recording.getCoordinate(i, 0));
        }
        
        // Testing my hairy ball
        Spatial football = assetManager.loadModel("Models/hairyBall/hairyBall.j3o");
        football.setLocalTranslation(0.0f, 0.0f, 0.0f);
        football.scale(0.5f, 0.5f, 0.5f);
        //moor di!
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        
        rootNode.attachChild(football);
        rootNode.addLight(sun);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}


//testtest1111111111
