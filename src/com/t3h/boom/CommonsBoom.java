package com.t3h.boom;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CommonsBoom {
	public static Image BULLET_EXPLOSION;
	
	public static Image TANK_EXPLOSION_1;
	public static Image TANK_EXPLOSION_2;
	public static Image TANK_EXPLOSION_3;
	public static Image TANK_EXPLOSION_4;
	public static Image TANK_EXPLOSION_5;
	
	public static final int EXPLOSION_BULLET_TYPE 	= 1;
	public static final int EXPLOSION_TANK_TYPE 	= 2;
	
	public CommonsBoom() {
		BULLET_EXPLOSION = getImage("/RESOURCE/Image/explosion.png");
		
		TANK_EXPLOSION_1 = getImage("/RESOURCE/Image/tank_exp1.png");
		TANK_EXPLOSION_2 = getImage("/RESOURCE/Image/tank_exp2.png");
		TANK_EXPLOSION_3 = getImage("/RESOURCE/Image/tank_exp3.png");
		TANK_EXPLOSION_4 = getImage("/RESOURCE/Image/tank_exp4.png");
		TANK_EXPLOSION_5 = getImage("/RESOURCE/Image/tank_exp5.png");
	}
	
	private Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
