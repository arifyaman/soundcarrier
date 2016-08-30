package com.xlip.soundcarrier.Units.Graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Xlip on 27.08.2016.
 */
public class BackGround {
    int repeat;


    public OrthographicCamera cam;
    public Array<Texture> textures;
    public Array<Float> oranlar;
    float look_for_repeat;


    public BackGround(Array<Texture> textures,OrthographicCamera camera){
        this.cam=camera;
        this.textures=textures;
        oranlar=new Array<Float>();
        oranlar.add(1f);
        oranlar.add(3.2f);
        oranlar.add(2.8f);
        oranlar.add(1.4f);
        oranlar.add(1f);
        repeat=2;
        look_for_repeat=camera.viewportWidth/2;



    }

    public void render(SpriteBatch batch){
        for (int i = 0; i <textures.size; i++) {
            TextureRegion reg=new TextureRegion(textures.get(i),0,0,textures.get(i).getWidth()*repeat,textures.get(i).getHeight());
            if(i==3)
                batch.draw(reg,-20,-15,40*repeat,30);
            else
            batch.draw(reg,(cam.position.x-20)-((cam.position.x+20)/oranlar.get(i)),-15,40*repeat,30);

        }

    }


    public void update( SpriteBatch batch){

        if(cam.position.x>look_for_repeat){
            System.out.println("artı1" +look_for_repeat );
            look_for_repeat+=cam.viewportWidth/2;
            repeat++;
        }
                render(batch);


    }
}
