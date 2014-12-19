package com.aajo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.kilobolt.GameWorld.GameRenderer;
import com.aajo.game.GameWorld;
//import com.kilobolt.ZBHelpers.InputHandler;
import com.aajo.game.BanalGame;

public class GameScreen implements Screen {

	private GameWorld world;

	public GameScreen(BanalGame banalGdxGame) {
		
		world = new GameWorld(this);
		Gdx.input.setInputProcessor(new InputHandler(world));

	}

	
	@Override
	public void render(float delta) {
		if(world.getPaused() == false) world.generalUpdate();
		
		world.render(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
