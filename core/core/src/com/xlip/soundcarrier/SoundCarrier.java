package com.xlip.soundcarrier;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.xlip.soundcarrier.Units.CameraScroller;
import com.xlip.soundcarrier.Units.Input;
import com.xlip.soundcarrier.Utils.Asset;
import com.xlip.soundcarrier.Utils.Ayar;

public class SoundCarrier extends ApplicationAdapter {

	public OrthographicCamera cam;
	public World world;
	public Input input;

	public final int GAME_READY=0;
	public final int GAME_RUNNING=1;
	public final int GAME_OVER=2;

	public int STATE;
	public CameraScroller cameraScroller;


	SpriteBatch batch;




	
	@Override
	public void create () {
		Asset.load();
		int scale=20;
		STATE=GAME_RUNNING;


		cam=new OrthographicCamera(Ayar.width/scale,Ayar.height/scale);
		batch=new SpriteBatch();



		input=new Input(this);


		Gdx.input.setInputProcessor(input);

		world=new World(this);
		cameraScroller=new CameraScroller(this);


	}

	@Override
	public void render () {
		float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f);
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		/*
		batch.setProjectionMatrix(cam.combined);
		batch.enableBlending();
		batch.begin();
		batch.draw(Asset.test,0,0,30,30);
		batch.end();
*/




		world.render(delta);
		cameraScroller.update(delta);


	}
}
