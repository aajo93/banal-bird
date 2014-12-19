package com.aajo.game;

import com.aajo.game.Assets;
import com.aajo.game.GameScreen;
import com.badlogic.gdx.Game;

public class BanalGame extends Game {

	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;
	public static final String TITLE = "Banal Bird";
	
	public GameScreen gameScreen;
	
	@Override
	public void create() {
		Assets.loadGraphics();
		Assets.loadFont();

		Assets.loadSound("assets/audio/flap.wav", "flap");
		Assets.loadSound("assets/audio/hit.wav", "hit");
		Assets.loadSound("assets/audio/score.wav", "score");
		Assets.loadSound("assets/audio/select.wav", "select");
		Assets.loadSound("assets/audio/tweet.wav", "tweet");
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.disposeSound("flap");
		Assets.disposeSound("hit");
		Assets.disposeSound("score");
		Assets.disposeSound("select");
		Assets.disposeSound("tweet");

	}

}