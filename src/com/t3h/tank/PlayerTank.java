package com.t3h.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.t3h.bullet.BulletManager;

public class PlayerTank extends Tank{
	
	private boolean upPressed 		= false;	//	Các biến boolean này dùng để kiểm tra xem
	private boolean downPressed 	= false;	// các nút di chuyển có được nhấn hay không
	private boolean leftPressed 	= false;	// nếu true thì nó sẽ di chuyển liên tục theo hướng đó
	private boolean rightPressed 	= false;	// nếu false thì dừng lại
	
	private boolean lockKey;

	public PlayerTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(CommonsTank.HEALTH_PLAYER_TANK);
		setImage();
		lockKey = false;
	}

	@Override	// Lấy ảnh Tank
	public void setImage() {
		UpImg 		= CommonsTank.UP_PLAYER_TANK;
		downImg		= CommonsTank.DOWN_PLAYER_TANK;
		leftImg		= CommonsTank.LEFT_PLAYER_TANK;
		rightImg	= CommonsTank.RIGHT_PLAYER_TANK;
	}
	@Override
	protected void drawHealth(Graphics2D g2d, int x, int y) {
		Image health;
		if (getHealth() <= 0) return;
		if (CommonsTank.HEALTH_PLAYER_TANK/getHealth() >= 2){
			health = CommonsTank.HEALTH_LOSE;
		}
		else health = CommonsTank.HEALTH_FULL;
		g2d.drawImage(health, x, y, CommonsTank.SIZE*getHealth()/CommonsTank.HEALTH_PLAYER_TANK, 4, null);
	}
	
	// Kiểm tra xem có trúng đạn hay không
	public boolean checkPlayerTank(BulletManager bulletMgr){
		int bulletEnemyX = 0;
		int bulletEnemyY = 0;
		int typeTankEnemy = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			bulletEnemyX = bulletMgr.getBullet(i).getX();
			bulletEnemyY = bulletMgr.getBullet(i).getY();
			typeTankEnemy = bulletMgr.getBullet(i).getType();
			if ((getX() <= bulletEnemyX && getX()+CommonsTank.SIZE >= bulletEnemyX) && (getY() <= bulletEnemyY && getY()+CommonsTank.SIZE >= bulletEnemyY) && typeTankEnemy==CommonsTank.BULLET_TYPE_ENEMY) {
				bulletMgr.removeBullet(i);
				setHealth(getHealth()-1);
				if (getHealth()<=0) return true;
			}
		}
		return false;
	}
	
	// Kiểm tra va chạm với các xe tăng địch
	public void checkImpact(EnemyTankManager enemyTankMgr){
		for (int i = 0; i < enemyTankMgr.getSize(); i++) {
			this.checkUp(enemyTankMgr.getEnemyTank(i));
			this.checkDown(enemyTankMgr.getEnemyTank(i));
			this.checkLeft(enemyTankMgr.getEnemyTank(i));
			this.checkRight(enemyTankMgr.getEnemyTank(i));
		}
	}
	
	// Khi nhấn nút thì Tank sẽ di chuyển liên tục theo nút đã nhấn
	public void pressKey(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:{
			rightPressed = true;		break;
		}
		case KeyEvent.VK_LEFT:{
			leftPressed = true;			break;
		}
		case KeyEvent.VK_UP:{
			upPressed = true;			break;
		}
		case KeyEvent.VK_DOWN:{
			downPressed = true;			break;
		}
		}
	}
	
	// Khi nhả nút thì dừng nhận lệnh di chuyển
	public void releaseKey(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:{
				rightPressed = false;		break;
			}
			case KeyEvent.VK_LEFT:{
				leftPressed = false;		break;
			}
			case KeyEvent.VK_UP:{
				upPressed = false;			break;
			}
			case KeyEvent.VK_DOWN:{
				downPressed = false;		break;
			}
		}
	}
		
	// Cho Tank chạy
	public void control(){
		if (lockKey) return;
		if (upPressed){
			move(CommonsTank.UP);
		}
		if (downPressed){
			move(CommonsTank.DOWN);
		}
		if (leftPressed){
			move(CommonsTank.LEFT);
		}
		if (rightPressed){
			move(CommonsTank.RIGHT);
		}
	}
	
	public void lockKey(){
		lockKey = true;
	}
	public void unlockKey(){
		lockKey = false;
	}
}
      