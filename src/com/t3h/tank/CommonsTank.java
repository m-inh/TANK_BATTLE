package com.t3h.tank;

import java.awt.Image;

import javax.swing.ImageIcon;

public class CommonsTank {
	public static final int UP_ORIENT 		= 1;
	public static final int DOWN_ORIENT		= 2;
	public static final int LEFT_ORIENT		= 3;
	public static final int RIGHT_ORIENT	= 4;
	public static final int UP			= 1;
	public static final int DOWN		= 2;
	public static final int LEFT		= 3;
	public static final int RIGHT		= 4;
	
	public static Image UP_PLAYER_TANK;
	public static Image DOWN_PLAYER_TANK;
	public static Image LEFT_PLAYER_TANK;
	public static Image RIGHT_PLAYER_TANK;
	
	public static Image UP_ENEMY_TANK;
	public static Image DOWN_ENEMY_TANK;
	public static Image LEFT_ENEMY_TANK;
	public static Image RIGHT_ENEMY_TANK;
	
	public static final int HEALTH_TANK = 25; // 	default health for each tank
	public static final int HEALTH_ENEMY_TANK = 25;
	public static final int HEALTH_PLAYER_TANK = 150;
	
	public static final int BULLET_TYPE_PLAYER = 1;
	public static final int BULLET_TYPE_ENEMY = 2;
	
	public CommonsTank() {
		UP_PLAYER_TANK = getImage("/RESOURCE/Image/player_green_1.png");
		DOWN_PLAYER_TANK = getImage("/RESOURCE/Image/player_green_2.png");
		LEFT_PLAYER_TANK = getImage("/RESOURCE/Image/player_green_3.png");
		RIGHT_PLAYER_TANK = getImage("/RESOURCE/Image/player_green_4.png");
		
		UP_ENEMY_TANK = getImage("/RESOURCE/Image/bossyellow_1.png");
		DOWN_ENEMY_TANK = getImage("/RESOURCE/Image/bossyellow_2.png");
		LEFT_ENEMY_TANK = getImage("/RESOURCE/Image/bossyellow_3.png");
		RIGHT_ENEMY_TANK = getImage("/RESOURCE/Image/bossyellow_4.png");
	}
	
	private Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
















