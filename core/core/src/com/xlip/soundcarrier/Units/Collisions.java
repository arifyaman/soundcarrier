package com.xlip.soundcarrier.Units;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.xlip.soundcarrier.Objs.Box;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Objs.Data.BodyData;

/**
 * Created by Xlip on 27.08.2016.
 */
public class Collisions implements ContactListener {
    SoundCarrier game;



    public Collisions(SoundCarrier game){
        this.game=game;



    }


    @Override
    public void beginContact(Contact contact) {

        Body a=contact.getFixtureA().getBody();
        Body b=contact.getFixtureB().getBody();

        if(get_data(b).string=="wirecell" ){
            game.world.wire.set_a_cell_collision(b,true);

        }
        if(get_data(a).string=="wirecell")
        {
            game.world.wire.set_a_cell_collision(a,true);
        }

        if(get_data(b).string=="player" && get_data(a).id==1)
           game.world.player.try_stay(a);
        if(get_data(a).string=="player" && get_data(b).string=="lastwirecell")
            game.world.wire.try_to_join();
        if(get_data(a).string=="yapiskan" && get_data(b).string=="lastwirecell"){
            Box temp=game.world.get_the_box(a);
            game.world.wire.try_to_yapis(temp.get_position().add(0,temp.wh.y));
        }
        if(get_data(b).string=="player" && (get_data(a).string=="deadly" || get_data(a).string=="knife"))
            game.world.player.die=true;

        if(get_data(b).string=="player" && get_data(a).string=="pole") {


           game.world.wire.connect_to_the_pole(game.world.get_the_box(a));

        }
        if(get_data(b).string=="wirecell" && get_data(a).string=="knife")
           game.world.wire.set_kopart_manuel(b);





    }

    @Override
    public void endContact(Contact contact) {
        Body a=contact.getFixtureA().getBody();
        Body b=contact.getFixtureB().getBody();

        if(get_data(b).string=="wirecell" ){
            game.world.wire.set_a_cell_collision(b,false);

        }
        if(get_data(a).string=="wirecell")
        {
            game.world.wire.set_a_cell_collision(a,false);
        }


        if(get_data(b).string=="player" && get_data(a).string=="pole") {
            game.world.wire.take_to_player_again();

        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Body a=contact.getFixtureA().getBody();
        Body b=contact.getFixtureB().getBody();
        if((get_data(b).string=="wirecell" || get_data(b).string=="lastwirecell" || get_data(b).string=="pole") && get_data(a).string=="player")
            contact.setEnabled(false);
        if((get_data(a).string=="wirecell" || get_data(a).string=="lastwirecell" || get_data(a).string=="pole") && get_data(b).string=="player")
            contact.setEnabled(false);


        if((get_data(b).string=="wirecell" || get_data(b).string=="lastwirecell") && get_data(a).string=="pole")
            contact.setEnabled(false);
        if(get_data(b).string=="player" && get_data(a).id!=1)
            contact.setEnabled(false);
        if(get_data(a).string=="pole" && get_data(b).string=="piece" )
            contact.setEnabled(false);

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    BodyData get_data(Body b){
       return  (BodyData)b.getUserData();

    }
}
