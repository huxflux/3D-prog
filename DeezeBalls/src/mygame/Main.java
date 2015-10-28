package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import java.util.ArrayList;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    public static void main(String[] args) {
        Main app = new Main();
        
        AppSettings newSetting = new AppSettings(true);
        newSetting.setFrameRate(60);
        app.setSettings(newSetting);
        app.start();
    }
    
    Recording recording;
    Spatial football;
    float[] test;
    int counter = 0;
    @Override
    public void simpleInitApp() {
        // Testing Coordinate class, it's aMaZzZiNg
        recording = new Recording("src\\mygame\\socket_2013011113_2810.dat");
        test = recording.getCoordinatesMarker(4);
        System.out.println(recording.toString());
        System.out.println(test.length);
       
        Box box1 = new Box(1,1,1);
        Geometry blue = new Geometry("Box", box1);
        blue.setLocalTranslation(new Vector3f(1,-1,1));
        Material mat1 = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        blue.setMaterial(mat1);

        // Testing my hairy ball
        football = assetManager.loadModel("Models/hairyBall/hairyBall.j3o");
        football.setLocalTranslation(0.0f, 0.0f, 0.0f);
        football.scale(1.0f, 1.0f, 1.0f);
        cam.setLocation(new Vector3f(0.0f, 0.0f, 10.0f));
        //moor di!
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -2.0f));
//        sun.setDirection(new Vector3f(0.0f, 0.0f, -2.0f));
//        PointLight sun = new PointLight();
//        sun.setPosition(Vector3f.ZERO);
        flyCam.setZoomSpeed(50);
        rootNode.attachChild(football);
        rootNode.attachChild(blue);
        rootNode.addLight(sun);
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        football.setLocalTranslation(test[counter+0], test[counter+1], test[counter+2]);
        counter += 3;
        if (counter >= recording.getNumberOfTimestamps()*3) {
            counter = 0;
        }
        
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}


//testtest1111111111
