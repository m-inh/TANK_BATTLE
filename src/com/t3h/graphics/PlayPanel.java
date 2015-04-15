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

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.Commons_Boom;
import com.t3h.bullet.Bullet;
import com.t3h.bullet.BulletManager;
import com.t3h.tank.EnemyTank;
import com.t3h.tank.EnemyTankManager;
import com.t3h.tank.PlayerTank;
import com.t3h.tank.Tank;

public class PlayPanel extends JPanel implements Runnable{
	private Graphics2D g2d;
	private int count;
	private Map map;
	private BulletManager bulletMgr;
	private BoomManager boomMgr;
	private PlayerTank playerTank;
	private EnemyTankManager enemyTankMgr;
	private Boom boom;
	private Thread th;
	
	public PlayPanel() {
		setBounds(0, 0, Commons.WIDTH_PANEL, Commons.HEIGHT_PANEL);
		setLayout(null);
		setBackground(Color.BLACK);
		setMap(1);
		boomMgr = new BoomManager();
		bulletMgr = new BulletManager();
		bulletMgr.setMap(this.map);
		bulletMgr.setBoomMgr(boomMgr);
		playerTank = new PlayerTank(350, 250, 4, 1);
		playerTank.setMap(map);
		enemyTankMgr = new EnemyTankManager();
		enemyTankMgr.setMap(map);
		enemyTankMgr.setBoomMgr(boomMgr);
		
		addKeyListener(moveAdapter);
		addMouseListener(click);
		th = new Thread(this);
		th.start();
		setFocusable(true);
	}
	
	private void setMap(int mapNumber){
		map = new Map(mapNumber);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		
		map.drawMap(g2d);
//		bulletMgr.drawAllBullet(g2d);
		playerTank.drawTank(g2d);
		enemyTankMgr.drawAllEnemyTank(g2d);
		boomMgr.exploredAllBoom(count, g2d);
	}
	
	private MouseAdapter click = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(null, e.getX() + " " + e.getY());
		}
	};
	
	private KeyAdapter moveAdapter = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
//			JOptionPane.showMessageDialog(null, "ok");
//			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
//				playerTank.move(4);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_LEFT){
//				playerTank.move(3);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_UP){
//				playerTank.move(1);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_DOWN){
//				playerTank.move(2);
//			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				bulletMgr.addBullet(new Bullet(playerTank.getX() + 13, playerTank.getY() + 13, 1, 1, 1, playerTank.getOrient()));
			}
			repaint();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			playerTank.checkImpact(enemyTankMgr);
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				playerTank.move(4);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				playerTank.move(3);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP){
				playerTank.move(1);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				playerTank.move(2);
			}
			repaint();
		}
	};
	
	@Override
	public void run() {
		while (true){
			
			
			bulletMgr.moveAllBullet();
			if (enemyTankMgr.getSize() < 10){
				EnemyTank enemyTank = new EnemyTank(30, 30, 1, 1);
				enemyTankMgr.addEnemyTank(enemyTank);
			}
			enemyTankMgr.checkImpact(playerTank);//-------------------------
			enemyTankMgr.AutoControlAllTank(count, bulletMgr);
			enemyTankMgr.checkAllEnemyTank(bulletMgr);
			playerTank.checkImpact(enemyTankMgr);//--------------------------
			playerTank.resetImpact();
			enemyTankMgr.resetImpact();
			if (playerTank.checkPlayerTank(bulletMgr)){
				//System.out.println("da bi ban chet");
				Boom boom = new Boom(playerTank.getX() + 16, playerTank.getY() + 16, Commons_Boom.EXPLOSION_TANK_TYPE);
				boomMgr.addBoom(boom);
			}
			count++;
			repaint();
			if (count > 10000) count = 0;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
