package com.t3h.tank;

public class PlayerTank extends Tank{

	public PlayerTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(commons.HEALTH_PLAYER_TANK);
		setImage();
	}

	@Override
	public void setImage() {
		UpImg = commons.UP_PLAYER_TANK;
		downImg = commons.DOWN_PLAYER_TANK;
		leftImg = commons.LEFT_PLAYER_TANK;
		rightImg = commons.RIGHT_PLAYER_TANK;
	}

}
      