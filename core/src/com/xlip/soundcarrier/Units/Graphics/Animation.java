package com.xlip.soundcarrier.Units.Graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


/**
 * Created by Xlip on 21.08.2016.
 */
public class Animation {
   public com.badlogic.gdx.graphics.g2d.Animation animation;
    float time;
    Array<TextureRegion> frames;
    float frameduration;
    com.badlogic.gdx.graphics.g2d.Animation.PlayMode playmode;
    public float angle;



    public int repeat;


    public Vector2 position;
    public Vector2 wh;
    public boolean just_one;



    public Animation(Texture texture, int x, int y, int w, int h, int repeat, float framedur, com.badlogic.gdx.graphics.g2d.Animation.PlayMode mode){

        this.repeat=repeat;
        playmode=mode;
        frameduration=framedur;


        frames=new Array<TextureRegion>();
        for (int i = 0; i <repeat ; i++) {
            frames.add(new TextureRegion(texture,x+i*w,y,w,h));
        }
        animation=new com.badlogic.gdx.graphics.g2d.Animation(framedur,frames,mode);
        just_one=false;
        angle=0;


        time=0;
    }



    public Animation set_angle(float a){
        this.angle=a;
        return this;
    }

public Animation flip_frames(boolean x,boolean y){


    for (TextureRegion t:frames
         ) {
        t.flip(x,y);
    }

    animation=new com.badlogic.gdx.graphics.g2d.Animation(animation.getFrameDuration(),frames,animation.getPlayMode());

    return this;

}

    public Animation(Animation anim){
        this.frames=new Array<TextureRegion>(anim.frames);

        this.wh=anim.wh;
        this.animation=new com.badlogic.gdx.graphics.g2d.Animation(anim.frameduration,frames,anim.playmode);
        this.position=anim.position;
        this.time=anim.time;
        this.just_one=anim.just_one;



    }

    public boolean is_finished(){
        return animation.isAnimationFinished(time)&&just_one;
    }

    public Animation set_pos(Vector2 pos){
        position=pos;
        return this;

    }

    public Animation set_wh(Vector2 wh){
        this.wh=wh;
        return this;

    }

    public Animation reset(){
        time=0;
        return this;

    }

    public Animation just_one(){
        just_one=true;
        return this;
    }

    public Animation change(Animation a){
        this.frames=a.frames;
        this.animation=a.animation;


        return this;

    }

    public TextureRegion get_texture(float delta){
        time+=delta;

        return animation.getKeyFrame(time);

    }




}
