package com.t3h.tank;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.t3h.graphics.Commons;

public class CommonsTank {
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
	
	public static final int SIZE = 35;
	
	// RADA dung de xac dinh khoang cach lon nhat ma EnemyTank co the phat hien ra playerTank
	public static final int RADA = 10*Commons.SIZE_COMPONENT;
	public static final int SUPER_RADA = 15*Commons.SIZE_COMPONENT;
	
	public static Image HEALTH_FULL;	// Mau cua Tank
	public static Image HEALTH_LOSE;
	
	public static final int HEALTH_TANK 		= 2; // 	default health for each tank
	public static final int HEALTH_ENEMY_TANK 	= 2;
	public static final int HEALTH_PLAYER_TANK 	= 4;
	
	public static final int BULLET_TYPE_PLAYER	= 1;
	public static final int BULLET_TYPE_ENEMY	= 2;
	
	public CommonsTank() {
		UP_PLAYER_TANK 		= getImage("/RESOURCE/Image/player_green_1.png");
		DOWN_PLAYER_TANK 	= getImage("/RESOURCE/Image/player_green_2.png");
		LEFT_PLAYER_TANK 	= getImage("/RESOURCE/Image/player_green_3.png");
		RIGHT_PLAYER_TANK 	= getImage("/RESOURCE/Image/player_green_4.png");
		
		UP_ENEMY_TANK 		= getImage("/RESOURCE/Image/bossyellow_1.png");
		DOWN_ENEMY_TANK 	= getImage("/RESOURCE/Image/bossyellow_2.png");
		LEFT_ENEMY_TANK 	= getImage("/RESOURCE/Image/bossyellow_3.png");
		RIGHT_ENEMY_TANK 	= getImage("/RESOURCE/Image/bossyellow_4.png");
		
		HEALTH_FULL	= getImage("/RESOURCE/Image/health_full.png");
		HEALTH_LOSE = getImage("/RESOURCE/Image/health_lose.png");
	}
	
	private Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}