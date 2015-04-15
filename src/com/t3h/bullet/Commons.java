package com.t3h.bullet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Commons {
	public static Image BULLET;
	
	public static final int SIZE_BULLET = 8;
	
	public static final int UP_ORIENT = 1;
	public static final int DOWN_ORIENT = 2;
	public static final int LEFT_ORIENT = 3;
	public static final int RIGHT_ORIENT = 4;
	
	public static final int BULLET_TYPE_PLAYER = 1;
	public static final int BULLET_TYPE_ENEMY = 2;
	
	public static final int BULLET_SPEED = 3;
	
	public Commons() {
		BULLET = getBulletImage("/RESOURCE/Image/bullet.png");
	}
	
	private Image getBulletImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
