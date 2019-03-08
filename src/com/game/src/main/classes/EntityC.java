package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityC {

	public void render(Graphics g);
	public Rectangle getBounds();		// Hit Box
	
	public void setSize();				// Image Resolution Size
	public String getID();				// Object ID
}