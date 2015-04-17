package com.t3h.tank;

import java.util.Random;

import com.t3h.bullet.Bullet;
import com.t3h.bullet.BulletManager;

public class EnemyTank extends Tank{
	private Random random;

	public EnemyTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(CommonsTank.HEALTH_ENEMY_TANK);
		setImage();
		
		random = new Random();
	}

	@Override
	public void setImage() {
		UpImg 		= CommonsTank.UP_ENEMY_TANK;
		downImg		= CommonsTank.DOWN_ENEMY_TANK;
		leftImg		= CommonsTank.LEFT_ENEMY_TANK;
		rightImg	= CommonsTank.RIGHT_ENEMY_TANK;
	}
	
	public void auto(int count, BulletManager bulletMgr){
		autoMove(count);
		autoFire(bulletMgr);
	}
	
	private void autoMove(int count){
		if (count % 50 == 0){
			int randomInt = random.nextInt();
			if (randomInt < 0){
				randomInt = randomInt*-1;
			}
			int newOrient = randomInt % 4 + 1;
			setOrient(newOrient);
//			JOptionPane.showMessageDialog(null, newOrient);
		}
		switch (getOrient()) {
		case 1:
			move(1);
			break;
		case 2:
			move(2);
			break;
		case 3:
			move(3);
			break;
		case 4:
			move(4);
			break;
		default:
			break;
		}
//		move(random.nextInt() % 4 + 1);
	}
	
	private void autoFire(BulletManager bulletMgr){
		if (random.nextInt(100) > 98){
			Bullet bullet = new Bullet(this.x+13, this.y+13, 2, 1, 1, getOrient());
			bulletMgr.addBullet(bullet);
		}
	}
	
	public void checkPlayer(PlayerTank playerTank){
		
	}


}
