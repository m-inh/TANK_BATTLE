package com.t3h.graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Commons {
	public static final int WIDTH_FRAME = 1000;
	public static final int HEIGHT_FRAME = 740;
	public static final int WIDTH_PANEL = 1000;
	public static final int HEIGHT_PANEL = 740;
	public static final int SIZE_COMPONENT = 25;
	public static final int sizeMap = 28;
	
	
	public static Image brick1;	// Gạch
	public static Image brick2;	// Gạch bị bắn
	public static Image stone;	// Đá
	public static Image componentDefault;	// Dùng để vẽ tphan mặc định, sau này phải xóa đi
	
	public Commons() {
		brick1 = getImage("/RESOURCE/Image/brick1.png");
		brick2 = getImage("/RESOURCE/Image/brick2.png");
		stone = getImage("/RESOURCE/Image/stone.png");
		componentDefault = getImage("/RESOURCE/Image/bullet.png");
	}
	
	public Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
}
