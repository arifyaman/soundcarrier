package com.xlip.soundcarrier.Units;

import com.badlogic.gdx.math.Vector2;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Utils.Sindeg;

/**
 * Created by Xlip on 27.08.2016.
 */
public class CameraScroller {
    Sindeg sindeg;
    SoundCarrier game;
    Vector2 limits;
    Vector2 target;
   public boolean running;

    float time=0;


    float tick_time=0.003f;


    public CameraScroller(SoundCarrier game){
        sindeg=new Sindeg(270,0.005f);

        this.game=game;
        limits=new Vector2(0,0);
        running=false;
    }

    public CameraScroller set_sindeg(float v){
        sindeg.set_deg(v);
        return this;

    }

    public void update(float delta){
        if(running) {
            time+=delta;
            if(time>=tick_time) {


                float deg = sindeg.getAngle();
                float x = get_ang_deg(deg, game.cam.position.x, target.x);
                game.cam.position.x = x;
                game.cam.update();
                System.out.println(sindeg.angle);
                if (sindeg.angle > 320 && Math.abs( game.cam.position.x-target.x)<1) {

                    after_run();
                    sindeg.reset();
                    this.running = false;
                }
                time=0;
            }

        }

    }

     float get_ang_deg(float deg,float bas,float bit){
        if(bas<bit)
            return ((deg+1)/2)*(bit-bas)+bas;
        else
            return bas-(((deg+1)/2)*(bas-bit));

    }


    public CameraScroller set_target(Vector2 tr){
        float x,y;

        if(tr.x<limits.x)x=0;
        else x=tr.x;
        if(tr.y<limits.y)y=0;
        else y=tr.y;

        target=new Vector2(x,y);
        running=true;
return this;
    }




    public void after_run(){


    }



}
