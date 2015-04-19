package com.t3h.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.CommonsBoom;
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
	private int mapNumber;
	private int tankPosition[];
	private BulletManager bulletMgr;
	private BoomManager boomMgr;
	private PlayerTank playerTank;
	private EnemyTankManager enemyTankMgr;
	private Thread th;
	
	private boolean playing;
	
	public PlayPanel() {
		setBounds(0, 0, Commons.WIDTH_PANEL, Commons.HEIGHT_PANEL);
		setLayout(null);
		setBackground(Color.BLACK);
		addKeyListener(moveAdapter);
		setFocusable(true);
		addMouseListener(click);
		mapNumber = 1;
		loadData();
		th = new Thread(this);
		th.start();
	}
	
	public void loadData(){
		playing = true;
		setMap(mapNumber);
		boomMgr = new BoomManager();
		bulletMgr = new BulletManager();
		bulletMgr.setMap(this.map);
		bulletMgr.setBoomMgr(boomMgr);
		playerTank = new PlayerTank(250, 630, 1, 40);
		playerTank.setMap(map);
		playerTank.setBoomMgr(boomMgr);
		enemyTankMgr = new EnemyTankManager();
		enemyTankMgr.setMap(map);
		enemyTankMgr.setBoomMgr(boomMgr);
		setTankPosition(mapNumber);
	}
	
	private void setMap(int mapNumber){
		map = new Map(mapNumber);
	}
	
	private void setTankPosition(int mapNumber){
		tankPosition = new int[4];
		switch (mapNumber) {
		case 1:
			tankPosition[0] = 30;
			tankPosition[1] = 220;
			tankPosition[2] = 380;
			tankPosition[3] = 620;
			break;
		case 2:
			tankPosition[0] = 30;
			tankPosition[1] = 235;
			tankPosition[2] = 440;
			tankPosition[3] = 620;
			break;
		case 3:
			tankPosition[0] = 30;
			tankPosition[1] = 220;
			tankPosition[2] = 320;
			tankPosition[3] = 620;
			break;
		case 4:
			tankPosition[0] = 30;
			tankPosition[1] = 220;
			tankPosition[2] = 485;
			tankPosition[3] = 620;
			break;
		case 5:
			tankPosition[0] = 180;
			tankPosition[1] = 280;
			tankPosition[2] = 380;
			tankPosition[3] = 500;
			break;
		default:
			break;
		}
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
	
		map.drawUnderComponent(g2d);
		bulletMgr.drawAllBullet(g2d);
		enemyTankMgr.drawAllEnemyTank(g2d);
		playerTank.drawTank(g2d);
		map.drawComponent(g2d);
		boomMgr.exploredAllBoom(count, g2d);
		
		if (!playing) drawGameOver(g2d);
	}
	
	private void drawGameOver(Graphics2D g2d){
		Commons common = new Commons();
		Image gameOver = common.getImage("/RESOURCE/Image/GameOver.png");
		g2d.drawImage(gameOver, 200, 250, null);
	}
	
	private MouseAdapter click = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(null, e.getX() + " " + e.getY());
		}
	};
	
	
	private long lastShoot = System.currentTimeMillis();//-----------------------------
	private long threshold = 300;//----------------------------------------------------
	private KeyAdapter moveAdapter = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			playerTank.releaseKey(e);
			if (playing && e.getKeyCode() == KeyEvent.VK_SPACE){
				long now = System.currentTimeMillis();
				if (now - lastShoot > threshold){
					bulletMgr.addBullet(new Bullet(playerTank.getX() + 13, playerTank.getY() + 13, 1, 1, 10, playerTank.getOrient()));
					Tank.sound.playShoot();
					lastShoot = now;
			     }
			}
			repaint();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			playerTank.checkImpact(enemyTankMgr);
			playerTank.pressKey(e);
			repaint();
		}
	};
	
	@Override
	public void run() {
		while (true){
			if (enemyTankMgr.getTankDestroy() >= 10){
				JOptionPane.showMessageDialog(null, "Win cmnr!");
				mapNumber++;
				if (mapNumber > 5){
					JOptionPane.showMessageDialog(null, "Win cmnr, bien cmm de!");
				}
				else loadData();
			}
			if (enemyTankMgr.getSize() < 5 && enemyTankMgr.getTotalTank() < 10){
				EnemyTank enemyTank = new EnemyTank(tankPosition[new Random().nextInt(4)], 30, 1, 50);
				enemyTankMgr.addEnemyTank(enemyTank);
			}
			if (count%enemyTankMgr.getEnemyTank(0).getSpeed() == 0){
				enemyTankMgr.checkImpact(playerTank);
				enemyTankMgr.AutoControlAllTank(count/enemyTankMgr.getEnemyTank(0).getSpeed(), bulletMgr, playerTank);
				enemyTankMgr.checkAllEnemyTank(bulletMgr);
				enemyTankMgr.resetImpact();
			}
			if (count%playerTank.getSpeed() == 0){
				playerTank.checkImpact(enemyTankMgr);
				playerTank.control();
				playerTank.resetImpact();
			}
			if (playerTank.checkPlayerTank(bulletMgr)){
				Boom boom = new Boom(playerTank.getX() + 16, playerTank.getY() + 16, CommonsBoom.EXPLOSION_TANK_TYPE);
				boomMgr.addBoom(boom);
				Tank.sound.playExplosionTank();
				if (playerTank.getHealth()<=0){
//					playing = false;
//					System.out.println("Thua cmnr");
//					playerTank.lockKey();
				}
			}
			if (count%Bullet.speed==0)	bulletMgr.moveAllBullet();
			
			count++;
			repaint();
			if (count > 1000000) count = 0;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
