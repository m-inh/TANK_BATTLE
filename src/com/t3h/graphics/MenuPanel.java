package com.t3h.graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton btnPlay;
	private GUI gui;
	
	public MenuPanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		init();
	}
	
	private void init(){
		btnPlay = new JButton();
		btnPlay.setBounds(100, 100, 100, 50);
		btnPlay.setText("Play");
		btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.showPlayGame();
			}
		});
		
		add(btnPlay);
	}
		
	public void setGUI(GUI g){
		gui = g;
	}
}
