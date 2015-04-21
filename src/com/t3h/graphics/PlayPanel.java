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
	
	private Map map;
	private int mapNumber;
	private int tankPosition[];
	
	private TankBase base;
	private BulletManager bulletMgr;
	private BoomManager boomMgr;
	private PlayerTank playerTank;
	private EnemyTankManager enemyTankMgr;
	
	private Thread th;
	private int count;
	
	private boolean playing;		// Khi dang choi thi playing = true, khi thua cuoc playing = false
	
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
	
//====================================== Draw ========================================
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
	
		map.drawUnderComponent(g2d);
		base.drawBase(g2d);
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

//==================================== Event =================================================
	
	private MouseAdapter click = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(null, e.getX() + " " + e.getY());
		}
	};
	
	
	// Bullet duoc ban it nhat sau 300ms
	private long lastShoot = System.currentTimeMillis();
	private long delay = 300;
	private KeyAdapter moveAdapter = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			playerTank.releaseKey(e);
			
			if (playing && e.getKeyCode() == KeyEvent.VK_SPACE){
				long now = System.currentTimeMillis();
				if (now - lastShoot > delay){
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

//================================== Thread =======================================
	
	@Override
	public void run() {
		while (true){
			checkWin();
			
			addNewEnemyTank();
			
			if (count%enemyTankMgr.getEnemyTank(0).getSpeed() == 0)	processEnemyTank();
			
			if (count%playerTank.getSpeed() == 0)	processPlayerTank();
			
			checkDie();
			
			if (count%Bullet.speed==0)	bulletMgr.moveAllBullet();
			
			count++;
			if (count > 1000000) count = 0;
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
//================================= Function ========================================
	
	private void loadData(){
		playing = true;
		setMap(mapNumber);
		base = new TankBase(325, 625, boomMgr);
		boomMgr = new BoomManager();
		bulletMgr = new BulletManager();
		bulletMgr.setMap(this.map);
		bulletMgr.setBoomMgr(boomMgr);
		playerTank = new PlayerTank(250, 630, 1, 40-mapNumber*5);
		playerTank.setMap(map);
		playerTank.setBoomMgr(boomMgr);
		enemyTankMgr = new EnemyTankManager();
		enemyTankMgr.setMap(map);
		enemyTankMgr.setBoomMgr(boomMgr);
		setTankPosition(mapNumber);
		Tank.sound.playEnterGame();
	}
	
	private void addNewEnemyTank(){
		if (enemyTankMgr.getSize() < 5 && enemyTankMgr.getTotalTank() < 10){
			EnemyTank enemyTank = new EnemyTank(tankPosition[new Random().nextInt(4)], 30, 1, 50-mapNumber*5);
			enemyTankMgr.addEnemyTank(enemyTank);
		}
	}
	
	private void checkDie(){
		if (playerTank.checkPlayerTank(bulletMgr)){
			Boom boom = new Boom(playerTank.getX() + 16, playerTank.getY() + 16, CommonsBoom.EXPLOSION_TANK_TYPE);
			boomMgr.addBoom(boom);
			Tank.sound.playExplosionTank();
		}
		if (playing && (playerTank.getHealth()<=0 || base.isDead(bulletMgr))){
			playing = false;
			playerTank.lockKey();
			Tank.sound.playEndGame();
			int value = JOptionPane.showConfirmDialog(null, "Chơi lại?");
			switch(value){
				case 2:
				case -1:
					mapNumber = 1;					// Nhan Cancel/X:	Quay ve Map 1
				case 0: loadData();		break;		// Nhan OK: 		Choi lai Map vua bi chet
			}
		}
	}
	
	private void checkWin(){
		if (enemyTankMgr.getTankDestroy() >= 10){
			JOptionPane.showMessageDialog(null, "Win!");
			mapNumber++;
			if (mapNumber > 5){
				JOptionPane.showMessageDialog(null, "Chiến thắng!!!Qua hết các cửa!\nQuay lại cửa 1");
				mapNumber = 1;
			}
			else loadData();
		}
	}
	
	private void processEnemyTank(){
		// Kiem tra va cham voi playerTank
		enemyTankMgr.checkImpact(playerTank);
		// Tu dong chay
		enemyTankMgr.AutoControlAllTank(count/enemyTankMgr.getEnemyTank(0).getSpeed(), bulletMgr, playerTank);
		// Kiem tra trung dan
		enemyTankMgr.checkAllEnemyTank(bulletMgr);
		// Reset: tat ca cac huong co the di duoc
		enemyTankMgr.resetImpact();
	}
	
	private void processPlayerTank(){
		// Kiem tra va cham voi enemyTank
		playerTank.checkImpact(enemyTankMgr);
		// Cho Tank di chuyen
		playerTank.control();
		// Reset: tat ca cac huong co the di duoc
		playerTank.resetImpact();
	}
	
	private void setMap(int mapNumber){
		map = new Map(mapNumber);
	}
	
	private void setTankPosition(int mapNumber){	// Vi tri xuat hien cua EnemyTank
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
}
