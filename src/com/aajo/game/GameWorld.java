package com.aajo.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.aajo.entities.Background;
import com.aajo.entities.Ground;
import com.aajo.entities.Player;

import com.aajo.entities.Wall;
import com.aajo.game.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	
	BitmapFont font; // scores
	BitmapFont bigFont; // pause and menu

	Player player;
	float angle; //angle of player
	int respawnTimer, score, highScore;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private ArrayList<Wall> walls;
		int spawnCount; // wall generate timer
	private ArrayList<Wall> checkPoints; //checkPoints are just invisible Walls
	private ArrayList<Ground> ground;

	public Background sky, mountains, trees;


	/*Handle these booleans outside of Game World?*/
	
	private boolean paused, started, quitting, yes;
	// yes = true, no = false


	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Constructor																							*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GameWorld(GameScreen gameScreen) {

		highScore = 0;
		font = Assets.font;
		bigFont = Assets.bigFont;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 640, 480);
		batch = new SpriteBatch();
		
		player = new Player();
		reset();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Reset level																							*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void reset() {
		Assets.playSound("tweet");
		score = respawnTimer = spawnCount= 0;
		
		walls = new ArrayList<Wall>();
		checkPoints = new ArrayList<Wall>();
		ground = new ArrayList<Ground>();

		Ground g;
		Point[] points = new Point[21];
		for (int i = 0; i < 21; i++) {
			points[i] = new Point(i * 32, 448);
		}
		for (int j = 0; j < points.length; j++) {
			g = new Ground();
			g.setBounds(new Rectangle(points[j].x, 448, 32, 32));
			ground.add(g);
		}

		sky = new Background(-0.2);
		sky.setImage(Assets.sprite_sky);
		
		mountains = new Background(-0.4);
		mountains.setImage(Assets.sprite_mountains);
		
		trees = new Background(-1.4);
		trees.setImage(Assets.sprite_trees);

		started = false;
		player.reset();

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Function for Wall Generation																		*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void createWall() { // make case statement?
		Random ran = new Random();
		int range = 6 - 1 + 1; // max - min + min
		int random = ran.nextInt(range) + 1; // add min

		//each wall is a 64 by 32 chunk 
		//which stack to create different structures.
		
		Wall w; 
		Point[] points = new Point[11];
		points[0] = new Point(640, -32);
		points[1] = new Point(640, 0);
		points[2] = new Point(640, 32); // top
		points[3] = new Point(640, 384);
		points[4] = new Point(640, 416);

		if (random == 1) {
			points[5] = new Point(640, 64);
			points[6] = new Point(640, 96);
			points[7] = new Point(640, 128); // top
			points[8] = new Point(640, 288);
			points[9] = new Point(640, 320);
			points[10] = new Point(640, 352); // bottom
		} else if (random == 2) {
			points[5] = new Point(640, 64);
			points[6] = new Point(640, 96); // top
			points[7] = new Point(640, 256);
			points[8] = new Point(640, 288); // bottom
			points[9] = new Point(640, 320);
			points[10] = new Point(640, 352); // bottom
		} else if (random == 3) {
			points[5] = new Point(640, 64);
			points[6] = new Point(640, 96);
			points[7] = new Point(640, 128); // top
			points[8] = new Point(640, 160);
			points[9] = new Point(640, 192); // top
			points[10] = new Point(640, 352); // bottom
		} else if (random == 4) {
			points[5] = new Point(640, 64);
			points[6] = new Point(640, 96);
			points[7] = new Point(640, 128); // top
			points[8] = new Point(640, 160);
			points[9] = new Point(640, 192);
			points[10] = new Point(640, 224); // top
		} else if (random == 5) {
			points[5] = new Point(640, 64); // top
			points[6] = new Point(640, 224);
			points[7] = new Point(640, 256);
			points[8] = new Point(640, 288); // bottom
			points[9] = new Point(640, 320);
			points[10] = new Point(640, 352); // bottom
		} else if (random == 6) {
			points[5] = new Point(640, 192);
			points[6] = new Point(640, 224);
			points[7] = new Point(640, 256); // bottom
			points[8] = new Point(640, 288);
			points[9] = new Point(640, 320);
			points[10] = new Point(640, 352); // bottom
		}

		for (int i = 0; i < points.length; i++) { // adds walls
			w = new Wall();
			w.setBounds(new Rectangle(points[i].x, points[i].y, 64, 32));
			walls.add(w);
		}
		Wall c; // adds a single checkpoint
		c = new Wall();
		checkPoints.add(c);
		c.setBounds(new Rectangle(640, 320, 32, 32));
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Get player for InputHandler																			*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Player getPlayer() {
		return player;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Render -- Draw Everything including Titles															*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 0.95F);
		batch.setProjectionMatrix(camera.combined);
		batch.begin(); // here goes the drawing

		batch.draw(Assets.sprite_sky,0,0);
		batch.draw(sky.getImage(), sky.getBounds().x, sky.getBounds().y);
		batch.draw(sky.getImage(), sky.getBounds().x, sky.getBounds().y);
		
		if (sky.getBounds().x < 0) {
			batch.draw(sky.getImage(), sky.getBounds().x + 640,
					sky.getBounds().y);
			if (sky.getBounds().x < -640) {
				sky.getBounds().x = 0;
			}
		}
		batch.draw(mountains.getImage(), mountains.getBounds().x,
				mountains.getBounds().y);
		if (mountains.getBounds().x < 0) {
			batch.draw(mountains.getImage(), mountains.getBounds().x + 640,
					mountains.getBounds().y);
			if (mountains.getBounds().x < -640) {
				mountains.getBounds().x = 0;
			}
		}
		batch.draw(trees.getImage(), trees.getBounds().x, trees.getBounds().y);
		if (trees.getBounds().x < 0) {
			batch.draw(trees.getImage(), trees.getBounds().x + 640,
					trees.getBounds().y);
			if (trees.getBounds().x < -640) {
				trees.getBounds().x = 0;
			}
		}

		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			batch.draw(w.getImage(), w.getBounds().x, w.getBounds().y);
		}

		//Don't draw checkPoints since they are invisible!
		
		/*
		 * for(int i = 0; i < checkPoints.size(); i++){ CheckPoint c =
		 * checkPoints.get(i); //this code for drawing the score-adding
		 * checkpoints batch.draw(c.getImage(),c.getBounds().x,c.getBounds().y);
		 * }
		 */

		for (int i = 0; i < ground.size(); i++) {
			Ground g = ground.get(i);
			batch.draw(g.getImage(), g.getBounds().x, g.getBounds().y);
		}

		batch.draw(player.getImage(), player.getBounds().x,
				player.getBounds().y, 16, 16, 32, 32, 1, 1,
				player.getAngle() * 5);

		font.draw(batch, ("Score: " + score), 8, 40); // 8, 40
		font.draw(batch, ("High Score: " + highScore), 280, 40); // 280,40
		
		 if(!started){font.draw(batch,("Press 'Space'"), 64, 148);}

		if (quitting) { // if quitting halt loop
			bigFont.draw(batch, ("Really Quit?"), 184, 232); // 184. 232
			font.draw(batch, ("Yes"), 240, 280); // 240, 280
			font.draw(batch, ("No"), 348, 280); // 348, 280
			if (yes) {
				font.draw(batch, ("-"), 224, 280);
			} else {
				font.draw(batch, ("-"), 332, 280);
			} // 224,280 && 332, 280
		} else if (paused) { // if paused halt loop
				bigFont.draw(batch, ("Paused"), 248, 248); // 248, 248
			}
		
		batch.end();	
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Update Positions																					*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void generalUpdate() {

		if (player.isDead()) {
			respawnTimer++;
			if (respawnTimer > 90) {
				reset();
			}
		}
		if (started) {
			player.generalUpdate();

			spawnCount++;
			if (spawnCount > 100) {
				createWall();
				spawnCount = 0;
			}
		}

		sky.generalUpdate();
		mountains.generalUpdate();
		trees.generalUpdate();

		//Cycle through walls ArrayList and update each one
		
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			w.generalUpdate();
		}
		//Same thing with Ground objects
		
		for (int i = 0; i < ground.size(); i++) {
			Ground g = ground.get(i);
			g.generalUpdate();

			//Also check each for collision with player
			
			if (player.getBounds().overlaps(g.getBounds())
					&& player.isDead() == false) {
				player.die();
				Assets.playSound("hit");
			}
		}
		
		//When walls go off screen, delete
		
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			if (w.getBounds().x < -64) {
				walls.remove(i);
				i--;
			}
		}

		for (int i = 0; i < checkPoints.size(); i++) {
			Wall c = checkPoints.get(i);
			c.generalUpdate();
		}

		for (int i = 0; i < checkPoints.size(); i++) {
			Wall c = checkPoints.get(i);
			if (c.getBounds().x <= player.getBounds().x) {
				score++;
				checkPoints.remove(i);
				i--;
				Assets.playSound("score");
			}
		}

		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			if (player.getBounds().overlaps(w.getBounds())
					&& player.isDead() == false) {
				player.die();
				Assets.playSound("hit");
			}
		}

		if (score > highScore) {
			highScore = score;
		}

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*	Getters && Setters																					*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setStarted(boolean started){
		this.started = started;
	}
	
	public boolean getStarted(){
		return started;
	}
	
	public void setPaused(boolean paused){
		this.paused = paused;
	}
	
	public boolean getPaused(){
		return paused;
	}
	
	public void setYes(boolean yes){
		this.yes = yes;
	}
	
	public boolean getYes(){
		return yes;
	}
	
	public void setQuitting(boolean quitting){
		this.quitting = quitting;
	}
	
	public boolean getQuitting(){
		return quitting;
	}
}
