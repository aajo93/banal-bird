package com.aajo.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class MapObject {

	private Sprite image;
	private Rectangle bounds;
	protected double hspeed;
	protected double vspeed;

	public void generalUpdate() {
		bounds.x += hspeed;
		bounds.y += vspeed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Sprite getImage() {
		return image;
	}

	public void setImage(Sprite image) {
		this.image = image;
	}
	
	public String getSwag(){
		
		return "SWAG";
		
	}
}
