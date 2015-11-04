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
    protected Geometry footballFieldGeo;
    protected float coordsX;
    protected float coordsY;
    protected float coordsZ;
    Recording recording;
    Spatial football;
    float[] test;
    int counter = 0;
    
    public static void main(String[] args) {
        Main app = new Main();
        
        AppSettings newSetting = new AppSettings(true);
        newSetting.setFrameRate(60);
        app.setSettings(newSetting);
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        // Testing Coordinate class, it's aMaZzZiNg
        recording = new Recording("src\\mygame\\socket_2013011113_2810.dat");
        test = recording.getCoordinatesMarker(1);
        System.out.println(recording.toString());
        System.out.println(test.length);
       
        // Testing my hairy ball
        football = assetManager.loadModel("Models/hairyBall/hairyBall.j3o");
        football.setLocalTranslation(0.0f, 0.0f, 0.0f);
        football.scale(1.0f, 1.0f, 1.0f);
        
        
        cam.setLocation(new Vector3f(200.0f, 300.0f, 600.0f));
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -2.0f));
        flyCam.setZoomSpeed(50);
        
        
        createFootballField();
        createCorner();
        rootNode.attachChild(football);
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
    
    public void setLight(){
        PointLight light = new PointLight();
        light.setPosition(Vector3f.ZERO);
        light.setRadius(300f);
        light.setColor(ColorRGBA.White);
        rootNode.addLight(light);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
    }
    
    
    public void createFootballField(){       
        Box footballField = new Box(105,68,1);//lengden må justeres slik at den passer fotballbanen
        footballFieldGeo = new Geometry("football_field",footballField);
        Material footballFieldMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        footballFieldMat.setTexture("DiffuseMap",assetManager.loadTexture("texture/grass.jpg"));
        footballFieldMat.setTexture("NormalMap", assetManager.loadTexture("texture/grass_normal.jpg"));
        footballFieldMat.setBoolean("UseMaterialColors", true);
        footballFieldMat.setColor("Diffuse", ColorRGBA.White);
        footballFieldMat.setColor("Specular", ColorRGBA.White);
        footballFieldGeo.setMaterial(footballFieldMat);
        footballFieldGeo.setLocalTranslation(new Vector3f(105,68,0));
        rootNode.attachChild(footballFieldGeo);
    }
    
    public void createBox(float coordsX, float coordsY, float coordsZ){
        Box box1 = new Box(3,3,1);
        Geometry blue = new Geometry("Box", box1);
        blue.setLocalTranslation(new Vector3f(coordsX, coordsY, coordsZ));
        Material mat1 = new Material(assetManager, 
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        blue.setMaterial(mat1);
        rootNode.attachChild(blue);
    }
    
    public void getCornerCoords(int corner){
        coordsX = 0;
        coordsY = 0;
        coordsZ = 0;
        recording = new Recording("src\\mygame\\socket_2013011113_2810.dat");//socket_2013011113_5843.dat
        for(int i = 0; i < recording.getNumberOfTimestamps(); i++){
            coordsX += recording.getCoordinate(i, corner);
            coordsY += recording.getCoordinate(i, corner+1);
            coordsZ += recording.getCoordinate(i, corner+2);
        }
        coordsX = coordsX/recording.getNumberOfTimestamps();
        coordsY = coordsY/recording.getNumberOfTimestamps();
        coordsZ = coordsZ/recording.getNumberOfTimestamps();
        System.out.println(coordsX + "  " + coordsY + "   " + coordsZ);
    }
    
    public void createCorner(){
        getCornerCoords(0);//venstre ned
        createBox(coordsX, coordsY,0);// har ikke satt inn z coordinat
        getCornerCoords(9);
        createBox(coordsY, coordsX,0);//høyre ned
        getCornerCoords(12);
        createBox(coordsY, coordsX,0);//16-meterstrek
        getCornerCoords(15);
        createBox(coordsY, coordsX,0);//midtbane
        getCornerCoords(18);
        createBox(coordsY, coordsX,0);//midtbane
        getCornerCoords(21);
        createBox(coordsY, coordsX,0);//midtbane
        getCornerCoords(24);
        createBox(coordsY, coordsX,0);//venstre opp
        getCornerCoords(27);
        createBox(coordsY, coordsX,0);//høyre opp
        getCornerCoords(30);
        createBox(coordsY, coordsX,0);
        getCornerCoords(33);
        createBox(coordsY, coordsX,0); 
        getCornerCoords(36);
        createBox(coordsY, coordsX,0); 
        getCornerCoords(39);
        createBox(coordsY, coordsX,0);    
    }
}
