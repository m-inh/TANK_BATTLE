package com.t3h.graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Commons {
	public static final int WIDTH_FRAME = 1000;
	public static final int HEIGHT_FRAME = 740;
	public static final int WIDTH_PANEL = 1000;
	public static final int HEIGHT_PANEL = 740;
	public static final int SIZE_COMPONENT = 25;
	
	// kich thuoc cua matrix[][]
	public static final int sizeMap = 28;
	
	
	public static Image brick1;	// Gáº¡ch
	public static Image brick2;	// Gáº¡ch bá»‹ báº¯n
	public static Image stone;	// Ä�Ã¡
	public static Image tree;
	public static Image componentDefault;	// DÃ¹ng Ä‘á»ƒ váº½ tphan máº·c Ä‘á»‹nh, sau nÃ y pháº£i xÃ³a Ä‘i
	
	public Commons() {
		brick1		= getImage("/RESOURCE/Image/brick1.png");
		brick2		= getImage("/RESOURCE/Image/brick2.png");
		stone		= getImage("/RESOURCE/Image/stone.png");
		tree		= getImage("/RESOURCE/Image/tree.png");
		componentDefault = getImage("/RESOURCE/Image/bullet.png");
	}
	
	public Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
