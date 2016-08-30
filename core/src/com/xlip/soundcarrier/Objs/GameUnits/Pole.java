package com.xlip.soundcarrier.Objs.GameUnits;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.xlip.soundcarrier.Objs.Box;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Utils.Asset;
import com.xlip.soundcarrier.World;

/**
 * Created by Xlip on 28.08.2016.
 */
public class Pole extends Box {



    public Pole(World world,Vector2 pos){
        super(BodyDef.BodyType.StaticBody,new Vector2(1f,5),pos,world.world2d );
        set_fixture(1,1,0).set_body_data(new BodyData("pole",0)).set_anim(Asset.pole);

       Animation phone=new Animation(Asset.phone_off).set_wh(new Vector2(0.8f,0.8f)).set_pos(pos.add(0.3f,-3.6f));
        world.phones.add(phone);


    }
}
