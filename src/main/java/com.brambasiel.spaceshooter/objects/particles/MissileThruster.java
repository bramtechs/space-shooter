package objects.particles;

import java.awt.Color;

public class MissileThruster extends Thruster {

	public MissileThruster() {
		color = Color.WHITE;
		delayBetweenSpawns = 0.05f;
		lifeTime = 150f;
	}

}
