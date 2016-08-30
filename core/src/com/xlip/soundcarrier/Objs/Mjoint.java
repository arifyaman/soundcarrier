package com.xlip.soundcarrier.Objs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

/**
 * Created by Xlip on 17.08.2016.
 */
public class Mjoint {
    Body bodya;
    Body bodyb;

    JointControl joint;
    MouseJointDef def;


    World world2d;



    public Mjoint(Body a, Body b, World world){
        bodya=a;
        bodyb=b;
        world2d=world;

        def=new MouseJointDef();
        def.collideConnected=true;
        def.bodyA=a;
        def.bodyB=b;
        def.maxForce=20000;

    }
    public Mjoint apply_joint(Vector2 target){
        destroy();

        def.target.set(bodyb.getWorldCenter());

        joint=new JointControl (world2d.createJoint(def),world2d,true);
        ((MouseJoint)joint.joint).setTarget(target);



        return this;
    }

    public Mjoint destroy(){
        if(joint!=null)
        joint.destroy();
        return this;
    }

}
