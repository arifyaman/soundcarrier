package com.xlip.soundcarrier.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Units.Graphics.Animation;

/**
 * Created by Xlip on 27.08.2016.
 */
public class Asset {


    public static Texture test;

    public static Array<Texture> background;
    public static Array<Animation> body_pieces;
    public static Texture guys;
    public static Texture lvl;
    public static TextureRegion joint;


    public static Animation stay;
    public static Animation stay_r;
    public static Animation walk;
    public static Animation walk_r;
    public static Animation jump;
    public static Animation sar;
    public static Animation sar_r;

    public static Animation wall;
    public static Animation yapiskan;
    public static Animation deadly;
    public static Animation pole;
    public static Animation piece;
    public static Animation blood;
    public static Animation wirecell;
    public static Animation success_piece;

    public static Animation knife;

    public static Animation phone_off;
    public static Animation phone_on;



    public static Animation cables;


    public static void load(){
        background=new Array<Texture>();
        body_pieces=new Array<Animation>();

        guys=new Texture(Gdx.files.internal("guys.png"));
        joint=new TextureRegion(guys,0,70,2,1);

        background.add(new Texture(Gdx.files.internal("back.png")));
        background.add(new Texture(Gdx.files.internal("b1.png")));
        background.add(new Texture(Gdx.files.internal("b2.png")));
        background.add(new Texture(Gdx.files.internal("b3.png")));

        init_background_prop();


        body_pieces.add(new Animation(guys,0,160,8,8,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP));
        body_pieces.add(new Animation(guys,8,160,8,8,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP));
        body_pieces.add(new Animation(guys,16,160,8,8,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP));
        body_pieces.add(new Animation(guys,24,160,8,8,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP));
        body_pieces.add(new Animation(guys,32,160,8,8,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP));

        stay=new Animation(guys,0,0,20,20,2,0.31f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        stay_r=new Animation(guys,0,0,20,20,2,0.31f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP).flip_frames(true,false);
        walk_r=new Animation(guys,0,20,20,20,3,0.11f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP).flip_frames(true,false);
        walk=new Animation(guys,0,20,20,20,3,0.11f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        jump=new Animation(guys,40,0,20,20,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        wall=new Animation(guys,0,40,25,25,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        pole =new Animation(guys,25,40,15,75,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        yapiskan =new Animation(guys,70,0,20,20,2,0.7f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        deadly =new Animation(guys,111,0,20,20,3,0.3f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
        piece =new Animation(guys,0,120,12,12,3,1.3f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        cables =new Animation(guys,0,135,15,20,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        sar =new Animation(guys,40,40,20,20,2,0.15f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        sar_r =new Animation(guys,40,40,20,20,2,0.15f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP).flip_frames(true,false);
        blood =new Animation(guys,70,20,10,10,4,0.065f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        wirecell =new Animation(guys,0,75,10,10,1,1f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        success_piece=new Animation(guys,36,120,12,12,3,0.03f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
        knife=new Animation(guys,50,80,12,12,3,0.10f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP);
        phone_off=new Animation(guys,0,170,35,35,1,0.15f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);
        phone_on=new Animation(guys,35,170,35,35,3,0.15f, com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG);


        lvl=new Texture(Gdx.files.internal("lvl.png"));
        test=new Texture(new FileHandle("test.png"));

    }

   static void init_background_prop(){
        for (int i = 0; i < background.size; i++) {
            background.get(i).setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        }

    }

}
