package com.t3h.graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Commons {
	public static final int WIDTH_FRAME 	= 710;
	public static final int HEIGHT_FRAME 	= 730;
	public static final int WIDTH_PANEL 	= 710;
	public static final int HEIGHT_PANEL 	= 730;
		
	public static final int sizeMap = 28;			// kich thuoc cua matrix[][]
	
	public static Image rim;	// Tuong
	public static Image brick1;	// Gach
	public static Image brick2;	// Gach bi ban
	public static Image stone;	// Da
	public static Image tree;	// Cay
	public static Image water;	// Nuoc
	
	public static Image base;		// Nha chu
	public static Image baseDead;	// Nha chu bi chet
	
	public static final int SIZE_COMPONENT 	= 25;	// Kich thuoc tung vien gach, tuong, cay,...
	
	public static final int NONE	= 0;		// Ma tung thanh phan trong Map
	public static final int RIM		= 11;		// duoc luu trong file Map
	public static final int BRICK1	= 1;
	public static final int BRICK2	= 10;
	public static final int STONE	= 2;
	public static final int TREE	= 5;
	public static final int WATER	= 3;
	
	public Commons() {
		base		= getImage("/RESOURCE/Image/base.png");
		baseDead	= getImage("/RESOURCE/Image/base_dead.png");
		brick1		= getImage("/RESOURCE/Image/brick1.png");
		brick2		= getImage("/RESOURCE/Image/brick2.png");
		stone		= getImage("/RESOURCE/Image/stone.png");
		tree		= getImage("/RESOURCE/Image/tree.png");
		water		= getImage("/RESOURCE/Image/water.png");
		rim			= getImage("/RESOURCE/Image/rim.png");
	}
	
	public Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
