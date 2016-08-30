package com.xlip.soundcarrier.Objs.GameUnits;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.xlip.soundcarrier.Objs.Box;
import com.xlip.soundcarrier.Objs.Data.BodyData;
import com.xlip.soundcarrier.Utils.Asset;
import com.xlip.soundcarrier.World;

/**
 * Created by Xlip on 28.08.2016.
 */
public class WireCell extends Box {
    public World world;
    public boolean there_is_a_collision;
    public boolean ben_koptum;


    public WireCell(World world, Vector2 pos){


        super(BodyDef.BodyType.DynamicBody,new Vector2(0.12f,0.12f),pos,world.world2d);
        there_is_a_collision=false;
        ben_koptum=false;

        set_fixture(0.01f,0.1f,0.1f).set_body_data(new BodyData("wirecell",0)).set_anim(Asset.wirecell);

        this.world=world;

    }
    public WireCell set_body_data(Object o){
        body.setUserData(o);
        return this;

    }


}
