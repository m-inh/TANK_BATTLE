package com.t3h.tank;

import java.util.Random;

import javax.swing.JOptionPane;

public class EnemyTank extends Tank{
	private Random random;

	public EnemyTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(commons.HEALTH_ENEMY_TANK);
		setImage();
		
		random = new Random();
	}

	@Override
	public void setImage() {
		UpImg = commons.UP_ENEMY_TANK;
		downImg = commons.DOWN_ENEMY_TANK;
		leftImg = commons.LEFT_ENEMY_TANK;
		rightImg = commons.RIGHT_ENEMY_TANK;
	}
	
	public void auto(int count){
		autoMove(count);
//		autoFire();
	}
	
	private void autoMove(int count){
		if (count % 100 == 0){
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
	
	private void autoFire(){
		if (random.nextInt(100) > 80){
			System.out.println("EnemyTank is Shotting");
		}
	}
	
	public void checkPlayer(PlayerTank playerTank){
		
	}


}
