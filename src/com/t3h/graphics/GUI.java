package com.t3h.graphics;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GUI extends JFrame{
	public final static int WIDTH_FRAME = 1000;
	public final static int HEIGHT_FRAME = 600;
	
	private PlayPanel playPanel;
	private MenuPanel menuPanel;
	
	public GUI(){
		double width_screen = getToolkit().getScreenSize().getWidth();
		double height_screen = getToolkit().getScreenSize().getHeight();
//		setBounds((int)(width_screen - Commons.WIDTH_FRAME) / 2, (int)(height_screen - Commons.HEIGHT_FRAME) / 2, Commons.WIDTH_FRAME, Commons.HEIGHT_FRAME);
		setBounds((int)(width_screen - Commons.WIDTH_FRAME) / 2, 0, Commons.WIDTH_FRAME, Commons.HEIGHT_FRAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new CardLayout());
		playPanel = new PlayPanel();
		menuPanel = new MenuPanel();
		add(playPanel);
		//add(menuPanel);
	}
}
