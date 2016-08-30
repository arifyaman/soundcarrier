package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Xlip on 18.08.2016.
 */
public class JointControl {
    public Joint joint;
    public World world2d;
    public boolean must_destroy;
    public boolean is_connected;

    public JointControl(Joint joint, World world, boolean bool){
        world2d=world;
        is_connected=bool;
        must_destroy=false;
        this.joint=joint;


    }

    public JointControl destroy(){
        if(is_connected){

            is_connected=false;
            world2d.destroyJoint(joint);
            return this;
        }else
            return this;



    }



}
