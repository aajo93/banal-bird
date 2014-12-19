package com.aajo.entities;

import com.aajo.game.Assets;
import com.badlogic.gdx.math.Rectangle;

public class Wall extends MapObject {

	public Wall() {
		setImage(Assets.sprite_wall);
		setBounds(new Rectangle(240, 360, 64, 32));
		hspeed = -2.4;
	}

}