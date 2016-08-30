package com.xlip.soundcarrier.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Objs.Pieces;
import com.xlip.soundcarrier.Objs.Wire;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Objs.Box;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Units.Graphics.BackGround;
import com.xlip.soundcarrier.Utils.Asset;


/**
 * Created by Xlip on 20.08.2016.
 */
public class WorldRenderer {
    public SoundCarrier game;
    public OrthographicCamera camera;
    SpriteBatch batch;
    float time;
   public Array<Animation> anims;
    BackGround background;
    ShapeRenderer shaperenderer;









    public WorldRenderer(SoundCarrier game, OrthographicCamera camera){
        anims=new Array<Animation>();
        this.game=game;
        this.camera=camera;
        batch=new SpriteBatch();

        background=new BackGround(camera);

        time=0;
        shaperenderer=new ShapeRenderer();





    }

    void update_anims(){

        for (int i = 0; i <anims.size ; i++) {
            Animation a=anims.get(i);
            if(a.is_finished())anims.removeIndex(i);
        }
    }

    void render_anim(Animation a,float delta){

        batch.draw(a.get_texture(delta),a.position.x-a.wh.x,a.position.y-a.wh.y,a.wh.x,a.wh.y,a.wh.x*2,a.wh.y*2
                ,1,1,0);

    }

    void render_anims(float delta){


        for (int i = 0; i <anims.size ; i++) {
            Animation a=anims.get(i);
            batch.draw(a.get_texture(delta),a.position.x-a.wh.x,a.position.y-a.wh.y,a.wh.x,a.wh.y,a.wh.x*2,a.wh.y*2
                    ,1,1,0);




        }
        update_anims();

    }

    public void render_boxes(Array walls, float delta){

        for (int i = 0; i <walls.size ; i++) {
            Box b=((Box)walls.get(i));

            Vector2 pos=b.body.getWorldCenter();
            if (b.animation != null ) {
                if (b==game.world.player && game.world.player.died)
                    continue;

                batch.draw(b.animation.get_texture(delta), pos.x - b.wh.x, pos.y - b.wh.y, b.wh.x, b.wh.y, b.wh.x * 2, b.wh.y * 2
                        , 1, 1, 0);

        }

            // batch.draw(b.animation.get_texture(delta),pos.x-b.wh.x,pos.y-b.wh.y,b.wh.x*2,b.wh.y*2);

                //batch.draw(b.animation.get_texture(delta),pos.x-b.wh.x,pos.y-b.wh.y,b.wh.x,b.wh.y,b.wh.x*2,b.wh.y*2,b.wh.x*2,b.wh.y*2,b.body.getAngle()/MathUtils.degRad);

        }


    }


void render_a_line(Vector2 v1,Vector2 v2,Color c){

    batch.end();
    shaperenderer.begin(ShapeRenderer.ShapeType.Line);
    shaperenderer.setProjectionMatrix(camera.combined);
    Gdx.gl20.glLineWidth(2.2f);
    shaperenderer.setColor(c);

    shaperenderer.line(v1.x, v1.y,v2.x, v2.y);
    shaperenderer.end();
    batch.setProjectionMatrix(camera.combined);
    batch.enableBlending();
    batch.begin();

}


public void render_wire_obj(Wire wire, float delta){
//first_pole ile imtihanı
    for (int i = 0; i <game.world.phones.size ; i++) {
        render_anim(game.world.phones.get(i),delta);
    }


    Color c;
    if(wire.it_is_done_with_this_wire)c=Color.GREEN;
    else c=Color.RED;


    render_a_line(wire.wirecelss.get(0).get_position(),new Vector2( wire.first_pole.get_position().add(0,3.4f)),c);

    for (int i = 1; i <wire.wirecelss.size ; i++) {
        if(wire.joints.get(i).is_connected)
        render_a_line(wire.wirecelss.get(i-1).get_position(),wire.wirecelss.get(i).get_position(),c);

    }
    if(wire.is_in_player_hand){
        render_a_line(wire.wirecelss.get(wire.wirecelss.size-1).get_position(),new Vector2( game.world.player.get_position().add(-0.4f,-0.4f)),c);

    }

    if(wire.twopoled)
        render_a_line(wire.wirecelss.get(wire.wirecelss.size-1).get_position(),new Vector2( wire.nex_pole.get_position().add(0,3.4f)),c);



    if(!wire.it_is_done_with_this_wire)
        render_boxes(wire.wirecelss,delta);



}


    public void render(float delta){
        time+=delta;

        batch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.enableBlending();
        background.update(batch);




        for (Wire w:game.world.all_wires
             ) {
            render_wire_obj(w,delta);
        }
        render_boxes(game.world.boxes,delta);


        render_anim(game.world.player.cable_anim,delta);



        for (Pieces p:game.world.pieces
             ) {
            render_boxes(p.pieces,delta);

        }
        render_anims(delta);

        batch.end();


    }
}
