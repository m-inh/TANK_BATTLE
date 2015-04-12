package com.t3h.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.t3h.bullet.Bullet;
import com.t3h.bullet.BulletManager;

public class PlayPanel extends JPanel implements Runnable{
	private Map map;
	private Bullet b;
	private Graphics2D g2d;
	private BulletManager bulletMgr;
	
	private int count;
	
	private Thread th;
	
	public PlayPanel() {
		setBounds(0, 0, Commons.WIDTH_PANEL, Commons.HEIGHT_PANEL);
		setLayout(null);
		setBackground(Color.BLACK);
		map = new Map(1);
		
		
		b = new Bullet(10, 10, 1, 1, 1, 2);
		bulletMgr = new BulletManager();
		th = new Thread(this);
		th.start();
		setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		
		map.drawMap(g2d);
	}
	
	@Override
	public void run() {
		while (b.getX() < Commons.WIDTH_PANEL){
			bulletMgr.moveAllBullet();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
