/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Lars Erik
 */
public class Square extends Node {
    
    private Material defaultUnshaded;
    private Vector3f punkt1;
    private Vector3f punkt2;
    private Vector3f punkt3;
    private Vector3f punkt4;
    private String farge;
    private AssetManager assetManager;
    
    
    public Square(AssetManager assetManager, Vector3f punkt1, Vector3f punkt2, Vector3f punkt3, Vector3f punkt4, String farge) {
        this.assetManager = assetManager;
        this.punkt1 = punkt1;
        this.punkt2 = punkt2;
        this.punkt3 = punkt3; 
        this.punkt4 = punkt4;
        this.farge = farge;
        drawSquare();
    }
    
    
    private void drawSquare() {
        Mesh mesh = new Mesh();
        
        Vector3f [] vertices = new Vector3f[4];
        vertices[0] = punkt1;
        vertices[1] = punkt2;
        vertices[2] = punkt3;
        vertices[3] = punkt4;
        
        Vector2f[] texCoord = new Vector2f[4];
        texCoord[0] = new Vector2f(0,0);
        texCoord[1] = new Vector2f(1,0);
        texCoord[2] = new Vector2f(0,1);
        texCoord[3] = new Vector2f(1,1);
        
        int [] indexes = { 2,0,1, 1,3,2 };
        
        mesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        mesh.setBuffer(VertexBuffer.Type.Index,    3, BufferUtils.createIntBuffer(indexes));
        mesh.updateBound();
        
        defaultUnshaded = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        
        Geometry rec = new Geometry("Football field", mesh);
        Texture t_rec;
        
        if(farge.equals("white")) {
            t_rec = assetManager.loadTexture("texture/white.png");
        }else {
            t_rec = assetManager.loadTexture("texture/green.png");
        }
        Material m_rec = defaultUnshaded.clone();
        m_rec.setTexture("ColorMap", t_rec);
        rec.setMaterial(m_rec);
        
        this.attachChild(rec);
        

    }
}
