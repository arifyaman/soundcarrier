package com.xlip.soundcarrier.Units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Objs.Pieces;
import com.xlip.soundcarrier.Units.Graphics.Animation;
import com.xlip.soundcarrier.Utils.Asset;

/**
 * Created by Xlip on 26.08.2016.
 */
public class Input implements InputProcessor {
SoundCarrier game;

    public Input(SoundCarrier game){
        this.game=game;

    }


    @Override
    public boolean keyDown(int keycode) {
if(game.STATE==game.GAME_RUNNING) {
    if (keycode == com.badlogic.gdx.Input.Keys.SPACE) {
        game.world.player.jump();

    }
    if (keycode == com.badlogic.gdx.Input.Keys.D)
        game.world.player.try_to_move(0);
    if (keycode == com.badlogic.gdx.Input.Keys.A)
        game.world.player.try_to_move(1);
    if (keycode == com.badlogic.gdx.Input.Keys.E)
        game.world.wire.add_cell(game.world.player.get_position());
    if (keycode == com.badlogic.gdx.Input.Keys.R)
        game.world.wire.remove_last();
    if (keycode == com.badlogic.gdx.Input.Keys.T) {

        System.out.println(game.world.wire.get_distance(game.world.wire.first_pole, game.world.wire.nex_pole) + "  " + game.world.wire.wirecelss.size);
        System.out.println(game.world.wire.is_it_free());


    }
}else if(game.STATE==game.GAME_OVER){
    if (keycode == com.badlogic.gdx.Input.Keys.X)

            game.world.replay(game.world.player.died);


}

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

if(game.STATE==game.GAME_RUNNING) {
    if (keycode == com.badlogic.gdx.Input.Keys.D)
        if (!Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A))
            game.world.player.try_to_move(-1);
        else
            game.world.player.try_to_move(1);
    else if (keycode == com.badlogic.gdx.Input.Keys.A)
        if (!Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D))
            game.world.player.try_to_move(-1);
        else
            game.world.player.try_to_move(0);
    else if (keycode == com.badlogic.gdx.Input.Keys.Q)
        game.world.wire.throw_it();


}
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 temp= convert_to_world(new Vector2(screenX,screenY));



       // game.world.mjoint.apply_joint(temp);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 temp= convert_to_world(new Vector2(screenX,screenY));


        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    Vector2 convert_to_world(Vector2 sc){
        Vector3 v=game.cam.unproject(new Vector3(sc.x,sc.y,0));
        return new Vector2(v.x,v.y);


    }
}
