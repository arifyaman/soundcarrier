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
public class Wall extends Box {
World world;

    public Wall(World world,Vector2 pos){
        super(BodyDef.BodyType.StaticBody,new Vector2(1,1),pos,world.world2d );
        this.world=world;


        set_fixture(1,1,0).set_body_data(new BodyData("ground",1)).set_anim(Asset.wall);

    }

    }



