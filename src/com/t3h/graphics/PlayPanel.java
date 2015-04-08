package com.t3h.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.t3h.bullet.Bullet;
import com.t3h.bullet.BulletManager;

public class PlayPanel extends JPanel implements Runnable{
	private Bullet b;
	private Graphics2D g2d;
	private BulletManager bulletMgr;
	
	private int demoX = 500;
	private int demoY = 300;
	private int count;
	
	private Thread th;
	
	public PlayPanel() {
		setBounds(0, 0, Commons.WIDTH_PANEL, Commons.HEIGHT_PANEL);
		setLayout(null);
		setBackground(Color.BLACK);
		
		b = new Bullet(10, 10, 1, 1, 1, 2);
		bulletMgr = new BulletManager();
		th = new Thread(this);
		th.start();
		setFocusable(true);
		addMouseListener(click);
		addKeyListener(keyBoard);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		
//		b.drawBullet(g2d);
		bulletMgr.drawAllBullet(g2d);
	}
	
	private MouseAdapter click = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			b.setStrong(b.getStrong()+1);
		}
	};
	
	private KeyAdapter keyBoard = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		public void keyReleased(KeyEvent e) {
			if( e.getKeyCode() == KeyEvent.VK_UP ){
				b = new Bullet(demoX, demoY, 1, 1, 1, 1);
				bulletMgr.addBullet(b);
//				JOptionPane.showMessageDialog(null, "ok");
			}
		};
		public void keyPressed(KeyEvent e) {
			if( e.getKeyCode() == KeyEvent.VK_ENTER ){
				count++;
				b = new Bullet(demoX, demoY, 1, 1, 1, count % 4 + 1);
				bulletMgr.addBullet(b);
//				JOptionPane.showMessageDialog(null, "ok");
			}
		};
	};

	@Override
	public void run() {
		while (b.getX() < Commons.WIDTH_PANEL){
//			b.move();
			bulletMgr.moveAllBullet();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
