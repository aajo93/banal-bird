package com.aajo.entities;

import com.badlogic.gdx.math.Rectangle;

public class Background extends MapObject{

	public Background(double d) {
		//setImage();
		setBounds(new Rectangle(0, 0, 640, 480));
		this.hspeed = d; //-0.4
	}
}
