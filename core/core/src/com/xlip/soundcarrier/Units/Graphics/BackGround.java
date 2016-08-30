package com.xlip.soundcarrier.Units.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Utils.Asset;


/**
 * Created by Xlip on 27.08.2016.
 */
public class BackGround {
    int repeat;


    public OrthographicCamera cam;
    public Array<Texture> textures;
    public Array<Float> oranlar;
    float look_for_repeat;
    TextureRegion render_reg;



    public BackGround(OrthographicCamera camera){
        this.cam=camera;
        textures=new Array<Texture>();
        textures.add(new Texture(Gdx.files.internal("back.png")));
        textures.add(new Texture(Gdx.files.internal("b1.png")));
        textures.add(new Texture(Gdx.files.internal("b2.png")));
        textures.add(new Texture(Gdx.files.internal("b3.png")));


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
        batch.enableBlending();
        batch.setProjectionMatrix(cam.combined);



        for (int i = 0; i <textures.size; i++) {
            Texture t=textures.get(i);


            for (int j = 0; j < repeat;j++) {
                if(i==3)
                batch.draw(t,-20+(j*40),-15,40,30);
                else
                    batch.draw(t,((cam.position.x-20)-((cam.position.x+20)/oranlar.get(i)))+(j*40),-15,40,30);
            }


            /*
            TextureRegion temp=new TextureRegion(t,0,0,t.getWidth()*repeat,t.getHeight());

            if(i==3)
                batch.draw(temp,-20,-15,40*repeat,30);
            else
            batch.draw(temp,(cam.position.x-20)-((cam.position.x+20)/oranlar.get(i)),-15,40*repeat,30);
*/
        }


    }


    public void update( SpriteBatch batch){

        if(cam.position.x>look_for_repeat){

            look_for_repeat+=cam.viewportWidth/2;
            repeat++;
        }
                render(batch);


    }
}
