package com.t3h.graphics;

import javax.swing.JFrame;

public class GUI extends JFrame{	
	private PlayPanel playPanel;
	private MenuPanel menuPanel;
	
	public GUI(){
		double width_screen = getToolkit().getScreenSize().getWidth();
		setBounds((int)(width_screen - Commons.WIDTH_FRAME) / 2, 0, Commons.WIDTH_FRAME, Commons.HEIGHT_FRAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		playPanel = new PlayPanel();
		playPanel.setBounds(0, 0, Commons.WIDTH_FRAME, Commons.HEIGHT_FRAME);
		add(playPanel);
		
//		menuPanel = new MenuPanel();
//		menuPanel.setBounds(0, 0, WIDTH_FRAME, HEIGHT_FRAME);
//		add(menuPanel);	
	}
	
	public void showPlayGame(){
		menuPanel.setVisible(false);
		playPanel.setVisible(true);
	}
	public void showMenuGame(){
		playPanel.setVisible(false);
		menuPanel.setVisible(true);
	}
}
