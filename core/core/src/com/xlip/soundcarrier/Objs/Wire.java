package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.utils.Array;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Objs.GameUnits.Pole;
import com.xlip.soundcarrier.Objs.GameUnits.WireCell;
import com.xlip.soundcarrier.Units.CameraScroller;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Utils.Asset;
import com.xlip.soundcarrier.World;

/**
 * Created by Xlip on 27.08.2016.
 */
public class Wire extends Obj {
    public Array<WireCell> wirecelss;
    public Array<JointControl> joints;
    World world;
    float time=0;



    Vector2 yapis_vec;
    public Box first_pole;



    public boolean is_in_player_hand;

    public boolean throwed;
   public boolean connected;
    boolean must_connect;
    boolean must_yapis;
    boolean koptu;


    boolean connect_to_the_pole;

    public Box nex_pole=null;

    public boolean twopoled;
    public boolean again_to_player;

    public boolean it_is_done_with_this_wire;







    public Wire(World world,Vector2 firstpos,Box firstpole){
        this.world=world;
        wirecelss=new Array<WireCell>();
        joints=new Array<JointControl>();
        connected=true;
        throwed=false;
        must_connect=false;
        must_yapis=false;
        koptu=false;
        is_in_player_hand=true;
        connect_to_the_pole=false;
        twopoled=false;
        again_to_player=false;
        it_is_done_with_this_wire=false;


        this.first_pole=firstpole;




        for (int i = 0; i <3 ; i++) {
            WireCell b=new WireCell(world,firstpos.add(0.3f*i,0));


            if(i==2)b.set_body_data(new BodyData("lastwirecell",0));

            wirecelss.add(b);

        }

        for (int i = 0; i <4; i++) {
            if(i==0)make_joint(first_pole,wirecelss.get(0));
            else if(i<3)
                make_joint(wirecelss.get(i-1),wirecelss.get(i));
            else
                make_joint(wirecelss.get(2),world.player);

        }


    }

    public void destroy(){



        for (Box b:wirecelss
             ) {
            b.destroy();
        }
    }

public void try_to_yapis(Vector2 y){

    if(throwed && !must_yapis ){

        must_yapis=true;
        yapis_vec=y.add(0,0.2f);
    }



}

    public void throw_it(){
        if(joints.size>2) {
            if(connected && !throwed) {
                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size - 1);
                is_in_player_hand = false;
                throwed = true;

            }
        }

    }

    void do_take_to_player(){
        if(twopoled && again_to_player && !connected  && !is_in_player_hand) {
            joints.get(joints.size - 1).destroy();
            joints.removeIndex(joints.size - 1);
            again_to_player = false;
            make_joint(wirecelss.get(wirecelss.size-1),world.player);
            twopoled=false;
            is_in_player_hand = true;
            connected = true;
        }

    }

    public void take_to_player_again(){
        if(twopoled && !connected && !is_in_player_hand) {
            again_to_player = true;

        }



    }

    public void connect_to_the_pole(Box b){
        if(world.game.STATE==world.game.GAME_RUNNING) {
            nex_pole = b;
            connect_to_the_pole = true;
        }

    }
     void do_connect_pole(Box b){

        if(joints.size>3 && connected && connect_to_the_pole && is_in_player_hand ) {
            if(first_pole.body!=b.body ) {

                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size - 1);
                make_joint(wirecelss.get(wirecelss.size - 1), b,true);
                is_in_player_hand = false;
                connected = false;
                connect_to_the_pole=false;
                twopoled=true;
                nex_pole=b;


            }

        }


    }

    public void remove_last(){
        if(joints.size>3 ) {
            if(connected) {
                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size - 1);
                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size - 1);


                wirecelss.get(wirecelss.size - 1).destroy();
                wirecelss.removeIndex(wirecelss.size - 1);

                make_joint(wirecelss.get(wirecelss.size - 1), world.player);
                wirecelss.get(wirecelss.size - 1).set_body_data(new BodyData("lastwirecell", 0));
            }else if(  twopoled){
                System.out.println("ttt");
                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size -1);
                joints.get(joints.size - 1).destroy();
                joints.removeIndex(joints.size - 1);

                wirecelss.get(wirecelss.size - 1).destroy();
                wirecelss.removeIndex(wirecelss.size - 1);
                wirecelss.get(wirecelss.size - 1).set_body_data(new BodyData("lastwirecell",0));

                make_joint(wirecelss.get(wirecelss.size - 1), nex_pole,true);


            }
        }

    }

    public void try_to_join(){
        if(!connected && !must_connect && !twopoled){
           must_connect=true;
        }

    }

    public void add_cell(Vector2 pos){
        if(connected) {
            joints.get(joints.size - 1).destroy();
            joints.removeIndex(joints.size - 1);


            wirecelss.add(new WireCell(world,pos.add(-world.player.wh.x,0)).set_body_data(new BodyData("lastwirecell", 0)));
            make_joint(wirecelss.get(wirecelss.size - 2).set_body_data(new BodyData("wirecell", 0)), wirecelss.get(wirecelss.size - 1));
            make_joint(wirecelss.get(wirecelss.size - 1), world.player);
        }
    }

    void make_joint(Box b1, Box b2){
        RopeJointDef ropeJointDef=new RopeJointDef();
        ropeJointDef.collideConnected=true;

        ropeJointDef.bodyA=b1.body;
        ropeJointDef.bodyB=b2.body;
        if(((BodyData)b1.body.getUserData()).string=="pole") {

            ropeJointDef.localAnchorA.set(new Vector2(0, 3.4f));
        }
        else
            ropeJointDef.localAnchorA.set(new Vector2(0,0));

        ropeJointDef.localAnchorB.set(new Vector2(0,0));
        ropeJointDef.maxLength=1.5f;
        joints.add( new JointControl ( world.world2d.createJoint(ropeJointDef),world.world2d,true));

    }

    void make_joint(Box b1, Box b2,boolean poleescape){
        RopeJointDef ropeJointDef=new RopeJointDef();
        ropeJointDef.collideConnected=true;

        ropeJointDef.bodyA=b1.body;
        ropeJointDef.bodyB=b2.body;
        if(((BodyData)b1.body.getUserData()).string=="pole") {

            ropeJointDef.localAnchorA.set(new Vector2(0, 3.4f));
        }
        else
            ropeJointDef.localAnchorA.set(new Vector2(0,0));
if(poleescape)
        ropeJointDef.localAnchorB.set(new Vector2(0, 3.4f));
        ropeJointDef.maxLength=1.5f;
        joints.add( new JointControl ( world.world2d.createJoint(ropeJointDef),world.world2d,true));

    }

    public void set_kopart_manuel(Body b){
        for (int i = 0; i < wirecelss.size; i++) {
            if(wirecelss.get(i).body==b)
                wirecelss.get(i).ben_koptum=true;

        }

    }

public int kopart_and_test_ok(){
    float t=get_voltage();

    for (int i = 0; i < wirecelss.size; i++) {
        if(wirecelss.get(i).ben_koptum)
            return i+1;

    }

    if(t>2.36 && twopoled && is_it_free()){
        boolean f=true;
        for (int i = 0; i <world.all_wires.size-1 ; i++) {
            if(world.all_wires.get(i)!=this)
            if(world.all_wires.get(i).first_pole.body==nex_pole.body || world.all_wires.get(i).nex_pole.body==nex_pole.body)
                f=false;
        }
        if(f) {

            it_is_done_with_this_wire = true;

            koptu = true;
            world.pieces.add(new Pieces(12, nex_pole.get_position().add(0, 3.4f), world).change_anims(Asset.success_piece).set_life(2.5f).change_wh(new Vector2(0.09f, 0.09f), false));
            world.checkpoint_pole=nex_pole;

            world.to_the_next_wire(false);
        }

    }

    if(t>3.6f)return 0;

    if(!twopoled) {
        for (int i = 1; i < wirecelss.size; i++) {
            if (get_distance(wirecelss.get(i - 1), wirecelss.get(i)) > 6.2f) return i;

        }
    }


return -1;

}

    public void proceed_success(){



    }

    public boolean is_it_free(){

        for (int i = 0; i <wirecelss.size ; i++) {
            if(wirecelss.get(i).there_is_a_collision)
                return false;
        }
        return true;
    }

    public void set_a_cell_collision(Body b,boolean v){

        for (int i = 0; i <wirecelss.size ; i++) {
            if(b==wirecelss.get(i).body)
                wirecelss.get(i).there_is_a_collision=v;


        }
    }

    public float get_voltage(){

        return get_distance(first_pole,world.player)/wirecelss.size;

    }





    public void update(float delta) {

        if (world.game.STATE == world.game.GAME_RUNNING) {

            if (!koptu) {
                do_take_to_player();


                do_connect_pole(nex_pole);

                if (twopoled && Gdx.input.isKeyPressed(Input.Keys.R))
                    world.world2d.step(delta, 2, 2);


                int kop_index = kopart_and_test_ok();
                if (kop_index > -1) {
                    final int a;
                    if (kop_index == 0)
                        a = 1 + random.nextInt(joints.size - 2);
                    else a = kop_index;

                    joints.get(a).destroy();
                    world.pieces.add(new Pieces(5, wirecelss.get(a).get_position(), world).set_life(3));
                    world.pieces.add(new Pieces(6, wirecelss.get(a - 1).get_position(), world).set_life(2.3f));

                    world.game.STATE = world.game.GAME_OVER;
                    connected = false;
                    is_in_player_hand = false;
                    koptu = true;


                    world.game.cameraScroller = new CameraScroller(world.game) {
                        @Override
                        public void after_run() {
                            super.after_run();
                        }
                    }.set_target(new Vector2(wirecelss.get(a - 1).get_position().x, 0)).set_sindeg(0.021f);


                }


                if (throwed) {
                    int a = world.player.dir == 0 ? 1 : -1;
                    time += delta;
                    if (time < 0.25f) {
                        wirecelss.get(wirecelss.size - 1).body.setLinearVelocity(new Vector2(30 * a, 30));
                    }

                    if (time > 1) {

                        time = 0;
                        throwed = false;
                        connected = false;
                    }

                }

                if (must_connect && !connected && !is_in_player_hand) {
                    time += delta;
                    if (time > 0.2f) {
                        throwed = false;
                        connected = true;
                        must_connect = false;
                        wirecelss.get(wirecelss.size - 1).body.setType(BodyDef.BodyType.DynamicBody);
                        make_joint(wirecelss.get(wirecelss.size - 1), world.player);
                        is_in_player_hand = true;
                        connect_to_the_pole = false;

                        time = 0;


                    }

                }

                if (must_yapis) {
                    wirecelss.get(wirecelss.size - 1).body.setTransform(yapis_vec, 0);
                    wirecelss.get(wirecelss.size - 1).body.setType(BodyDef.BodyType.StaticBody);
                    yapis_vec = null;
                    is_in_player_hand = false;
                    connected = false;
                    must_yapis = false;

                }


            }
        }
    }

}
