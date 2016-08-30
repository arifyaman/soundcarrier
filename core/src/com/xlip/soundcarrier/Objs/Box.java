package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.xlip.soundcarrier.Units.Graphics.Animation;


/**
 * Created by Xlip on 17.08.2016.
 */
public class Box extends Obj {
    public Body body;
    public Vector2 wh;
    public Animation animation;
    World world;

    public Box(Box b){
        this.body=b.body;
        wh=b.wh;
        animation=b.animation;
        world=b.world;

    }

public Box(){

}
    public Box(BodyDef.BodyType type, Vector2 wh, Vector2 pos, World world,float density){

        this.wh=wh;
        this.world=world;

        BodyDef def=new BodyDef();
        def.type= type;
        def.position.set(pos);

        Body body= world.createBody(def);
        PolygonShape shape= new PolygonShape();
        shape.setAsBox(wh.x,wh.y);
        FixtureDef fdef=new FixtureDef();
        fdef.shape= shape;
        fdef.density=density;
        fdef.friction=0.3f;
        fdef.restitution=0.5f;
        body.createFixture(fdef);



        this.body=body;


    }

    public Box(BodyDef.BodyType type, Vector2 wh, Vector2 pos, World world){

        this.wh=wh;
        this.world=world;

        BodyDef def=new BodyDef();
        def.type= type;
        def.position.set(pos);

        Body body= world.createBody(def);




        this.body=body;


    }

    public Box set_fixture(float density,float friction, float rest){
        PolygonShape shape= new PolygonShape();
        shape.setAsBox(wh.x,wh.y);
        FixtureDef fdef=new FixtureDef();
        fdef.shape= shape;
        fdef.density=density;
        fdef.friction=friction;
        fdef.restitution=rest;
        body.createFixture(fdef);

        return this;
    }


    public Vector2 get_position(){
        return body.getPosition();

    }
    public float get_bottom(){
        return body.getPosition().y-wh.y;

    }

    public float get_top(){
        return body.getPosition().y+wh.y;

    }
    public float get_left(){
        return body.getPosition().x-wh.x;

    }
    public float get_right(){
        return body.getPosition().x+wh.x;

    }

    public Box set_anim(Animation a){
        animation=new Animation(a);

        return this;
    }
    public Box set_body_data(Object o){
        body.setUserData(o);
        return this;

    }

    public void destroy(){

        world.destroyBody(body);

    }


}
