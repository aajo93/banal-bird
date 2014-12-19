package com.aajo.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.aajo.entities.Player;
import com.aajo.game.GameWorld;
import com.aajo.game.Assets;

public  class InputHandler implements InputProcessor {
	
	private GameWorld world;
	private Player player;

	public InputHandler(GameWorld world) {
		
		
		this.world = world;
		player = world.getPlayer();
	}

	@Override
	public boolean keyDown(int keycode) {

		if (keycode == Keys.SPACE && !player.isDead()) {
			player.jump();
			Assets.playSound("flap");

			if (world.getStarted() == false) { // jump to start game
				world.setStarted(true);
			}
		}
		if (keycode == Keys.P && world.getQuitting() == false) {
			Assets.playSound("select");

			if (world.getPaused() == false && world.getStarted() == true) {
				world.setPaused(true);
			} else {
				world.setPaused(false);
			}
		}
		
		if (keycode == Keys.ESCAPE) {
			Assets.playSound("select");

			if (world.getQuitting() == false) {
				world.setQuitting(true);
			} else {
				world.setQuitting(false);
				world.setYes(true);
			}
		}

		if (keycode == Keys.LEFT && world.getQuitting() == true) {
			world.setYes(true);
			Assets.playSound("select");
		}
		if (keycode == Keys.RIGHT && world.getQuitting() == true) {
			world.setYes(false);
			Assets.playSound("select");
		}
		if (keycode == Keys.ENTER && world.getQuitting() == true) {
			if (world.getYes() == true) {
				Gdx.app.exit();
			} else {
				world.setQuitting(false);
				world.setYes(false);
			}
		}
		return true;
	}

	@Override
	public boolean keyTyped(char arg0) {return false;}

	@Override
	public boolean keyUp(int arg0) {return false;}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {return false;}

	@Override
	public boolean scrolled(int arg0) {return false;}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		//User can now click to jump instead of mashing SPACE
		
		if (!player.isDead()) {
			world.player.jump();
			Assets.playSound("flap");

			if (world.getStarted() == false) { // jump to start game
				world.setStarted(true);
			}
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {return false;}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {return false;}
}
