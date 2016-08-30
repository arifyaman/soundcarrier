package com.xlip.soundcarrier;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Objs.Box;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Objs.GameUnits.Knife;
import com.xlip.soundcarrier.Objs.GameUnits.Pole;
import com.xlip.soundcarrier.Objs.GameUnits.Scorp;
import com.xlip.soundcarrier.Objs.GameUnits.Wall;
import com.xlip.soundcarrier.Objs.GameUnits.Yapiskan;
import com.xlip.soundcarrier.Objs.Mjoint;
import com.xlip.soundcarrier.Objs.Pieces;
import com.xlip.soundcarrier.Objs.Player;
import com.xlip.soundcarrier.Objs.Wire;
import com.xlip.soundcarrier.Units.CameraScroller;
import com.xlip.soundcarrier.Units.Collisions;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Units.WorldRenderer;
import com.xlip.soundcarrier.Utils.Asset;

/**
 * Created by Xlip on 26.08.2016.
 */
public class World {
    public Collisions collisions;


    public com.badlogic.gdx.physics.box2d.World world2d;
    public SoundCarrier game;
    Box2DDebugRenderer box2DDebugRenderer;
   public WorldRenderer worldrenderer;
    public Box current_pole;

    public Array<Box> boxes;

    public Mjoint mjoint;
    public Array<Pieces> pieces;
    public Player player;

    public Wire wire;

    public Array<Wire> all_wires;


    public Array<Animation> phones;
    public int phone_anim_control;

   public Box checkpoint_pole;











    public World(SoundCarrier game){
        this.game=game;
        boxes=new Array<Box>();
        pieces=new Array<Pieces>();
        all_wires=new Array<Wire>();
        phones=new Array<Animation>();


        world2d=new com.badlogic.gdx.physics.box2d.World(new Vector2(0,-50),true);
        box2DDebugRenderer=new Box2DDebugRenderer();


        collisions=new Collisions(game);
        world2d.setContactListener(collisions);

        init_level(Asset.lvl);
        player=new Player(new Box(BodyDef.BodyType.DynamicBody,new Vector2(1,1),new Vector2(-16f,-13),world2d).set_fixture(1,0,0.12f),this);

        boxes.add(player);


        mjoint=new Mjoint(boxes.get(0).body,boxes.get(1).body,world2d);

        wire=new Wire(this,new Vector2(-17,-5),current_pole);

        all_wires.add(wire);

        worldrenderer=new WorldRenderer(this.game,this.game.cam);



    }


    void init_level(Texture t){

        boxes.add(new Box(BodyDef.BodyType.StaticBody,new Vector2(200,1),new Vector2(0,-14),world2d).set_fixture(1,1,0).set_body_data(new BodyData("ground",1)));


        current_pole= new Pole(this, new Vector2(-18,-8));

        checkpoint_pole=current_pole;

        boxes.add(current_pole);
        boxes.add(new Box(BodyDef.BodyType.StaticBody,new Vector2(1,20),new Vector2(-21,1),world2d).set_fixture(1,1,0).set_body_data(new BodyData("ground",1)).set_anim(Asset.wall));



        //-14 ground
        //pole ground -8

        if(!t.getTextureData().isPrepared())t.getTextureData().prepare();
        Pixmap map=t.getTextureData().consumePixmap();

        for (int i = 0; i <map.getWidth() ; i++) {
            for (int j = 0; j <map.getHeight() ; j++) {
                int a=map.getPixel(i,j);
                System.out.println(a);
                if(a==255)

                    boxes.add(new Wall(this,new Vector2(-18+i*2,16+0.2f-j*2)));
                else if(a==-16776961)
                    boxes.add(new Scorp(this,new Vector2(-18+i*2,16-0.6f-j*2)));

                else if(a==16716287)
                    boxes.add(new Yapiskan(this,new Vector2(-18+i*2,16-0.3f-j*2)));
                else if(a==-83951361)
                    boxes.add(new Pole(this,new Vector2(-18+i*2,16+4-j*2)));
                else if(a==-7533313)
                    boxes.add(new Knife(this,new Vector2(-18+i*2,16-0.6f-j*2)));

            }

        }


    }



    public void to_the_next_wire(boolean replay){


            Wire new_wire=new Wire(this,wire.nex_pole.get_position(),wire.nex_pole);


            phones.get(phone_anim_control).change(Asset.phone_on);
            phones.get(phone_anim_control + 1).change(Asset.phone_on);
            phone_anim_control += 1;

            all_wires.add(new_wire);
            wire=new_wire;




    }

    public void replay(boolean player_died){
        game.STATE=game.GAME_RUNNING;

        if(player_died) {
            boxes.removeValue(player,true);
            player.destroy();
            player = new Player(new Box(BodyDef.BodyType.DynamicBody, new Vector2(1, 1),all_wires.get(all_wires.size-1).first_pole.get_position(), world2d).set_fixture(1, 0, 0.12f), this);
            boxes.add(player);
        }
            else {
            player.body.setTransform(checkpoint_pole.get_position(), 0);


        }

        Wire new_wire= new Wire(this,checkpoint_pole.get_position(),checkpoint_pole);
        wire=new_wire;
        all_wires.get(all_wires.size-1).destroy();
        all_wires.removeIndex(all_wires.size-1);
        all_wires.add(new_wire);





        game.cameraScroller=new CameraScroller(game).set_target(player.get_position()).set_sindeg(0.021f);

    }


    public void update(float delta){

            update_pieces(delta);
            player.update(delta);
            wire.update(delta);

            if (game.STATE == game.GAME_RUNNING && !game.cameraScroller.running)
                if (player.get_position().x + 4 > 0)
                    game.cam.position.x = player.get_position().x + 4;
            game.cam.update();

    }

    public void render(float delta){

        update(delta);
            world2d.step(delta,2,2);
        worldrenderer.render(delta);

       //d box2DDebugRenderer.render(world2d,game.cam.combined);






    }



    public Box get_the_box(Body b){
        for (Box box:boxes
             ) {
            if(box.body==b)
                return box;
        }
        return null;


    }


    void update_pieces(float delta){
        for (Pieces p:pieces
             ) {
            if(!p.update(delta))
                pieces.removeValue(p,true);
        }
    }

}
