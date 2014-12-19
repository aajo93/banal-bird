package com.aajo.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = BanalGame.TITLE;
		cfg.useGL20 = true;
        cfg.vSyncEnabled = true;
		cfg.width = BanalGame.WIDTH; //640
		cfg.height = BanalGame.HEIGHT; //480
		cfg.resizable = false;
		
		new LwjglApplication(new BanalGame(), cfg);
	}
}
