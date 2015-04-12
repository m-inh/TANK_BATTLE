package com.t3h.graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class Map {
	private int type;
	private Image bground;
	
	private File file;
	private RandomAccessFile rdf;
	
	private int matrix[][];
	
	Commons common = new Commons();
	public Map(int type) {
		this.type = type;
		bground = common.getImage("/RESOURCE/Image/bg_contai_panel.png");
		matrix = new int [Commons.sizeMap][Commons.sizeMap];
		
//		String pathMap =  "/RESOURCE/Map/map" + type;
		String pathMap = "C://Users/TMQ/Documents/TANK_BATTLE/src/RESOURCE/Map/map" + type;
		file = new File(pathMap);
//		if (file.exists()) JOptionPane.showMessageDialog(null, "File ton tai");
//		else JOptionPane.showMessageDialog(null, "file k ton tai");
		openFile();
		readFile();
		closeFile();
	}
	
	public void drawMap(Graphics2D g2d){
		g2d.drawImage(bground, 0, 0, 700, 700, null);
		drawComponent(g2d);
	}
	private void drawComponent(Graphics2D g2d){
		int type, x, y;
		for (int i=0; i<Commons.sizeMap; i++){
			for (int j=0; j<Commons.sizeMap; j++){
				type = matrix[i][j];
				x = i+1;	y = j+1;
				switch(type){
					case 0:{
						break;
					}
					case 1:{
						g2d.drawImage(Commons.brick1, x*Commons.SIZE_COMPONENT, y*Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, null);
						break;
					}
					case 2:{
						g2d.drawImage(Commons.stone, x*Commons.SIZE_COMPONENT, y*Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, null);
						break;
					}
					case 3:{
						g2d.drawImage(Commons.brick2, x*Commons.SIZE_COMPONENT, y*Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, Commons.SIZE_COMPONENT, null);
						break;
					}
					default:{	// chỉ Demo, sau này phải xóa cái ảnh này	
						g2d.drawImage(Commons.componentDefault, x*Commons.SIZE_COMPONENT, y*Commons.SIZE_COMPONENT, null);
						break;
					}
				}
			}
		}
	}
	
	//-----------------------------FILE
	private void openFile(){
		try {
			if (file.exists()){
				rdf = new RandomAccessFile(file, "rw");
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy Map");
		}
	}
	private void closeFile(){
		try {
			rdf.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể đóng file Map");
		}
	}
	private long filePointer(){
		try {
			return rdf.getFilePointer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	private String readLine(){
		try {
			return rdf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	private void readFile(){
		long lenFile = file.length();
		String line = "";
		int pos, i, j, type;
		while (filePointer()<lenFile){
			line = readLine();
			pos = line.indexOf(" ");
			i = Integer.parseInt(line.substring(0, pos));
			j = Integer.parseInt(line.substring(pos+1, line.length()));
			type = Integer.parseInt(readLine());
			matrix[i][j] = type;
		}
	}
	
	
}
