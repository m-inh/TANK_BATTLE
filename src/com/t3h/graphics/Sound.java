package com.t3h.graphics;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static AudioClip SHOOT;
	public static AudioClip MOVE;
	public static AudioClip EXPLOSION;
	public static AudioClip EXPLOSION_TANK;
	
	public Sound() {
		setSound(true);
	}
	public void setSound(boolean power) {
		// checkSound=flag;
		if (power == true) {
			SHOOT = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/shoot.wav"));
			MOVE = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/move.wav"));
			EXPLOSION = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/explosion.wav"));
			EXPLOSION_TANK = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/explosion_tank.wav"));
	
		} else {
			SHOOT = null;
			MOVE = null;
			EXPLOSION = null;
			EXPLOSION_TANK = null;
		}
	}

	public void playShoot() {
		if (SHOOT != null) {
			SHOOT.play();
		}
	}

	public void playMove() {
		if (MOVE != null) {
			MOVE.play();
		}
	}

	public void playExplosion() {
		if (EXPLOSION != null) {
			EXPLOSION.play();
		}
	}

	public void playExplosionTank() {
		if (EXPLOSION_TANK != null) {
			EXPLOSION_TANK.play();
		}
	}
}
