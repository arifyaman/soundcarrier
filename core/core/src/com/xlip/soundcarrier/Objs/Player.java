package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Utils.Asset;
import com.xlip.soundcarrier.World;

/**
 * Created by Xlip on 27.08.2016.
 */
public class Player extends Box {
    World world;
    public final int RIGHT=0;
    public final int LEFT=1;

    public final int UP=0;
    public final int DOWN=1;
    public final int stay=2;

    public int state;
    public int dir;

    float jumpforce=5300;
    float linear_velocity=8.0f;
    float up_time=0;
    public Animation cable_anim;
    public boolean die;
    public boolean died;










    public Player(Box b, World world){
        super(b);
        this.world=world;
        die=false;
        died=false;


        state=stay;
        dir=RIGHT;
        set_body_data(new BodyData("player",1));
        set_anim(Asset.stay);
        cable_anim=new Animation(Asset.cables).set_wh(new Vector2(0.4f,0.5f));



    }

    public void jump(){
        if(state==stay) {
            body.applyForceToCenter(0, jumpforce, true);
            state=UP;

        }
    }

    public void try_to_move(int dir){
        if(dir==RIGHT){
            body.setLinearVelocity(new Vector2(linear_velocity,body.getLinearVelocity().y));
            animation.change(Asset.walk);
            this.dir=RIGHT;

        }else if(dir==LEFT){
            body.setLinearVelocity(new Vector2(-linear_velocity,body.getLinearVelocity().y));
            animation.change(Asset.walk_r);
            this.dir=LEFT;

        }else if(dir==-1){

            body.setLinearVelocity(new Vector2(0,body.getLinearVelocity().y));
           anim_to_dir(0);

        }

    }

    void anim_to_dir(int i){
        if(i==0) {
            if (dir == RIGHT) animation.change(Asset.stay);
            else animation.change(Asset.stay_r);
        }else if(i==1){
            if (dir == RIGHT) animation.change(Asset.sar);
            else animation.change(Asset.sar_r);
        }
    }


    public void try_stay(Body b){
        Box box= world.get_the_box(b);

        if(Math.abs( get_bottom()-box.get_top())<0.4f){
            anim_to_dir(0);
            body.setLinearVelocity(0,0);
            state = stay;

        }

    }
    public void destroy(){
        world.world2d.destroyBody(body);

    }

    public void update(float delta){
        up_time+=delta;
if(die)die();

        cable_anim.set_pos(new Vector2(get_position()).add(-0.4f,-0.4f));
        body.setAngularVelocity(0);

        if(up_time>=0.4f) {

            if (Math.abs(body.getLinearVelocity().y) > 1) {
                animation.change(Asset.jump);

            } else
                anim_to_dir(0);
up_time=0;
        }

        if(world.game.STATE==world.game.GAME_RUNNING) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) try_to_move(1);
            if (Gdx.input.isKeyPressed(Input.Keys.E)) anim_to_dir(1);
            if (Gdx.input.isKeyPressed(Input.Keys.R)) anim_to_dir(1);
            if (Gdx.input.isKeyPressed(Input.Keys.D)) try_to_move(0);
        }else
            try_to_move(-1);



    }

    public void die(){
        if(die && !died) {
            world.pieces.add(new Pieces(13, get_position(), world).set_life(2 + random.nextInt(4)).set_gore(Asset.blood).change_anims(Asset.body_pieces).change_wh(new Vector2(0.18f,0.18f),false));
            world.pieces.add(new Pieces(5, get_position(), world).set_life(3 + random.nextInt(3)).set_gore(Asset.blood).change_anims(Asset.body_pieces).change_wh(new Vector2(0.18f,0.18f),false));
            body.applyForce(new Vector2(400+random.nextInt(500)*get_rnd10(),400+random.nextInt(500)),new Vector2(random.nextFloat()*2,random.nextFloat()*2),true);
            world.game.STATE=world.game.GAME_OVER;
            died=true;
            die=false;
        }

    }

}
