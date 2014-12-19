package com.aajo.entities;

import com.aajo.game.Assets;
import com.badlogic.gdx.math.Rectangle;

public class Player extends MapObject {

	private boolean dead;
	private float angle;

	public Player() {
		setImage(Assets.sprite_bird2);
		reset();
	}
	
	public void reset() {
		setBounds(new Rectangle(128, 156, 32, 32));
		vspeed = 0;
		hspeed = 0;
		dead = false;
		setAngle(0);
	}

	public void generalUpdate() {
		super.generalUpdate();

		vspeed += 0.2;
		setAngle((float) vspeed);

		if (vspeed > 9) {
			vspeed = 9;
		}

		if (vspeed >= 0) {
			setImage(Assets.sprite_bird2);
		} else if (vspeed < 0) {
			setImage(Assets.sprite_bird);
		}

		if (getBounds().y < -48) {
			vspeed = 1;
		}
	}

	public void jump() {
		vspeed = -5.4;
	}

	public void die() {
		vspeed = -7;
		hspeed = -4;
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

}
