package com.aajo.game;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;

public class Assets{
	public static Texture texture_box1;
	public static Sprite sprite_box1;
	public static Texture texture_bird;
	public static Sprite sprite_bird;
	public static Texture texture_bird2;
	public static Sprite sprite_bird2;
	public static Texture texture_wall;
	public static Sprite sprite_wall;
	public static Texture texture_ground;
	public static Sprite sprite_ground;

	public static Texture texture_sky;
	public static Sprite sprite_sky;

	public static Texture texture_mountains;
	public static Sprite sprite_mountains;

	public static Texture texture_trees;
	public static Sprite sprite_trees;

	
	public static BitmapFont font; // scores
	public static BitmapFont bigFont; // pause and menu
	public static final String FONT_CHARACTERS =
	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"�`'<>";
	
	private static HashMap<String, Sound> sounds;

	static {
		sounds = new HashMap<String, Sound>();
	}

	public static void loadGraphics() {
		
		texture_box1 = new Texture(Gdx.files.internal("assets/images/box1.png"));
		sprite_box1 = new Sprite(texture_box1);
		sprite_box1.flip(false, true);
		texture_bird = new Texture(Gdx.files.internal("assets/images/bird.png"));
		sprite_bird = new Sprite(texture_bird);
		sprite_bird.flip(false, true);
		texture_bird2 = new Texture(Gdx.files.internal("assets/images/bird2.png"));
		sprite_bird2 = new Sprite(texture_bird2);
		sprite_bird2.flip(false, true);
		texture_wall = new Texture(Gdx.files.internal("assets/images/block.png"));
		sprite_wall = new Sprite(texture_wall);
		sprite_wall.flip(false, true);
		texture_ground = new Texture(Gdx.files.internal("assets/images/ground.png"));
		sprite_ground = new Sprite(texture_ground);
		sprite_ground.flip(false, true);
		
		texture_sky = new Texture(Gdx.files.internal("assets/images/sky.png"));
		sprite_sky = new Sprite(texture_sky);
		sprite_sky.flip(false, true);
		
		texture_mountains = new Texture(
				Gdx.files.internal("assets/images/mountains.png"));
		sprite_mountains = new Sprite(texture_mountains);
		sprite_mountains.flip(false, true);
		texture_trees = new Texture(Gdx.files.internal("assets/images/trees.png"));
		sprite_trees = new Sprite(texture_trees);
		sprite_trees.flip(false, true);

	}

	public static void loadFont(){
		
		font =
				TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("assets/SIXTY.ttf"),
				FONT_CHARACTERS, 16.5f, 9.5f, 1.0f, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

			font.setColor(1f, 0f, 0f, 1f);
			font.setScale(1, -1); // flips text
				
		bigFont =
				TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("assets/SIXTY.ttf"),
				FONT_CHARACTERS, 10.5f, 6.0f, 1.0f, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		
			bigFont.setColor(1f, 0f, 0f, 1f);
			bigFont.setScale(1, -1); // flips text
			
	}
	public static void loadSound(String path, String name) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(name, sound);
	}

	public static void playSound(String name) {
		sounds.get(name).play();
	}

	public static void stopAllSound(String name) {
		for (Sound s : sounds.values()) {
			s.stop();
		}
	}
	
	public static void disposeSound(String name){
		for (Sound s : sounds.values()){
			s.dispose();
		}
	}
	
	public static void removeAll(){
		texture_bird.dispose();
		
	}

}