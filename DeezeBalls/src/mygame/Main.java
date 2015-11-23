package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import java.awt.Color;
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
    private Material defaultUnshaded;
    Circle2d circle;
    
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
        flyCam.setMoveSpeed(200);
        
        
        //createFootballField();
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
    
    /*
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
    } */
    
    public void getCornerCoords(int corner){
        coordsX = 0;
        coordsY = 0;
        coordsZ = 0;
        recording = new Recording("src\\socket_data\\socket_20151112_132337.dat");//socket_2013011113_5843.dat
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
        Vector3f node1 = new Vector3f(coordsX, coordsY, 0);
        
        getCornerCoords(3);
        Vector3f node2 = new Vector3f(coordsX, coordsY, 0);
        
        getCornerCoords(6);
        Vector3f node3 = new Vector3f(coordsX, coordsY, 0);
        
        getCornerCoords(9);
        Vector3f node4 = new Vector3f(coordsX, coordsY, 0);
        
        getCornerCoords(12);
        Vector3f node5 = new Vector3f(coordsX, coordsY, 0);

        getCornerCoords(15);
        Vector3f node6 = new Vector3f(coordsX, coordsY, 0);

        getCornerCoords(18);
        Vector3f node7 = new Vector3f(coordsX, coordsY, 0);

        getCornerCoords(21);
        Vector3f node8 = new Vector3f(coordsX, coordsY, 0);
        
        //main field
        CreateQuad((node6.getX()-node1.getX())*2, (node2.getY()-node1.getY())*2, "white", 0, 0, 0);
        CreateQuad((node6.getX()-node1.getX())*2 - 10, (node2.getY()-node1.getY())*2 - 10, "green", 5, 5, 0.1f);
        
        //16-meter white
        CreateQuad(((node6.getX()-node1.getX())*2)-((node3.getX() - node2.getX())*2), node3.getY()-node4.getY(), "white", (node3.getX()-node2.getX()),0, 0.2f);        
        CreateQuad(((node6.getX()-node1.getX())*2)-((node3.getX() - node2.getX())*2), node3.getY()-node4.getY(), "white", (node3.getX()-node2.getX()), (node2.getY()-node1.getY())*2-(node3.getY()-node4.getY()), 0.2f);
        
        //16-meter green
        CreateQuad(((node6.getX()-node1.getX())*2)-((node3.getX() - node2.getX())*2) - 10, node3.getY()-node4.getY() - 10, "green", (node3.getX()-node2.getX()) + 5,0 + 5, 0.3f);        
        CreateQuad(((node6.getX()-node1.getX())*2)-((node3.getX() - node2.getX())*2) - 10, node3.getY()-node4.getY() - 10, "green", (node3.getX()-node2.getX()) + 5, (node2.getY()-node1.getY())*2-(node3.getY()-node4.getY()) + 5, 0.3f);
        
        //5-meter white
        CreateQuad((node6.getX()-node1.getX())*2-(node5.getX()-node2.getX())*2, (node5.getY()-node8.getY()), "white", (node5.getX()-node2.getX()), 0, 0.4f);
        CreateQuad((node6.getX()-node1.getX())*2-(node5.getX()-node2.getX())*2, (node5.getY()-node8.getY()), "white", (node5.getX()-node2.getX()), (node2.getY()-node1.getY())*2-(node5.getY()-node8.getY()), 0.4f);

        //5-meter green
        CreateQuad((node6.getX()-node1.getX())*2-(node5.getX()-node2.getX())*2 - 10, (node5.getY()-node8.getY()) - 10, "green", (node5.getX()-node2.getX()) + 5, 0 + 5, 0.5f);
        CreateQuad((node6.getX()-node1.getX())*2-(node5.getX()-node2.getX())*2 - 10, (node5.getY()-node8.getY()) - 10, "green", (node5.getX()-node2.getX()) + 5, (node2.getY()-node1.getY())*2-(node5.getY()-node8.getY()) + 5, 0.5f);
        
        //mid line
        CreateQuad((node6.getX()-node1.getX())*2, 5, "white", 0, node2.getY()-node1.getY() - 2.5f, 0.3f);
        
        //mid circle
        circle = new Circle2d(assetManager, (node7.getX()-node6.getX())*2, 2.5f, Color.WHITE, 360, Color.red, 0);
        circle.rotate(FastMath.PI/2, 0, 0);
        circle.setLocalTranslation(node6.getX()-node1.getX() - (node7.getX()-node6.getX()), node2.getY()-node1.getY() - (node7.getX()-node6.getX()), 0.4f);
        rootNode.attachChild(circle);
        
        //mid dot
        circle = new Circle2d(assetManager, (10)*2, 2.5f, Color.WHITE, 360, Color.WHITE, 360);
        circle.rotate(FastMath.PI/2, 0, 0);
        circle.setLocalTranslation(node6.getX()-node1.getX() - 10, node2.getY()-node1.getY() - 10, 0.5f);
        rootNode.attachChild(circle);
    }
    
    
    public void CreateQuad(float tall1, float tall2, String color, float trans1, float trans2, float trans3) {
        Quad quad = new Quad(tall1, tall2);
        Geometry field = new Geometry("Field", quad);
        field.setLocalTranslation(new Vector3f(trans1, trans2, trans3));
        defaultUnshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture texture;
        
        if(color.equals("white")) {
            texture = assetManager.loadTexture("texture/white.png");
        } else {
            texture = assetManager.loadTexture("texture/green.png");
        }
        Material material = defaultUnshaded.clone();
        material.setTexture("ColorMap", texture);
        field.setMaterial(material);

        rootNode.attachChild(field);
    }
}
