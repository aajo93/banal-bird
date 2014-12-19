package com.aajo.entities;

import com.aajo.game.Assets;
import com.badlogic.gdx.math.Rectangle;

public class Ground extends MapObject {

	public Ground() {
		setImage(Assets.sprite_ground);
		setBounds(new Rectangle(240, 360, 64, 32));
		hspeed = -2.4;
	}

	public void generalUpdate() {
		super.generalUpdate();

		if (getBounds().x < -32) {
			getBounds().x += 672;
		}
	}
}
