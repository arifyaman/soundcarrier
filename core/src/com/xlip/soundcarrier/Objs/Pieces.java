package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Utils.Asset;

/**
 * Created by Xlip on 26.08.2016.
 */
public class Pieces extends Obj {
   public Array<Box> pieces;
    float life;
    float time;
    float gore_time=0.02f;

    boolean make_gore;
    Animation gore;
    com.xlip.soundcarrier.World world;




    public Pieces(int sayi, Vector2 pos, com.xlip.soundcarrier.World world){
        pieces=new Array<Box>();
        life=-1;
        time=0;
        make_gore=false;
        this.world=world;


        for (int i = 0; i <sayi ; i++) {
            Box t=new Box(BodyDef.BodyType.DynamicBody,new Vector2(0.15f,0.04f),pos,world.world2d,0.6f).set_body_data(new BodyData("piece",i)).set_anim(Asset.piece).set_fixture(0.25f,0,0.4f);
            t.body.applyForceToCenter(random.nextInt(10)*get_rnd10()*t.wh.x*20,random.nextInt(50)*get_rnd10()*t.wh.y*20,true);

            pieces.add(t);

        }



    }

public Pieces set_gore(Animation a){

    gore=a;
    make_gore=true;

    return this;
}
    public Pieces change_wh(Vector2 wh,boolean t){
        for (Box b:pieces
                ) {
            b.wh=wh;
            if(t)
            b.body.applyForceToCenter(random.nextInt(10)*get_rnd10()*b.wh.x*30,random.nextInt(50)*get_rnd10()*b.wh.y*30,true);

        }

        return this;

    }

    public Pieces change_anims(Array<Animation> as){
        for (Box b:pieces
                ) {
            b.animation.change(as.random());

        }
        return this;

    }

    public Pieces change_anims(Animation as){
        for (Box b:pieces
                ) {
            b.animation.change(as);

        }
        return this;

    }

    public Pieces set_life(float time){
        life=time;
        return this;

    }

    public void destroy(){
        for (int i = 0; i <pieces.size ; i++) {
            pieces.get(i).destroy();
        }
    }

    public boolean update(float delta){
        if(time<life && life>0) {
            time += delta;
            if(time>=gore_time && make_gore) {
                gore_time+=0.03f;

                make_gore();




            }

        }
else
        return false;

        if(life>0)
        if(time>=life) {
            destroy();
            life=-1;
        }
return true;
    }



    void make_gore(){



        for (Box b:pieces
             ) {


                float addx= random.nextFloat()/4*get_rnd10();
                float addy= random.nextFloat()/4*get_rnd10();
                world.worldrenderer.anims.add(new Animation(gore).set_pos(new Vector2( b.get_position()).add(addx,addy)).set_wh(new Vector2(0.05f,0.06f)).just_one());



        }

    }

}
