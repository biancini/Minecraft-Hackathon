package it.reti.minecraft.plugin.sample.tasks;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.animal.Cow;
import net.canarymod.api.world.position.Location;
import net.canarymod.tasks.ServerTask;

public class MuccaTask extends ServerTask {
	private Cow cow;
	
	public MuccaTask(Cow cow) {
		super(Canary.getServer(), 0, true);
		this.cow = cow;
	}
	
	public void run() {
		if (cow.isOnGround()) {
			Location loc = cow.getLocation();
			cow.setHealth(0);
			cow.kill();
			cow.getWorld().makeExplosion(cow,  loc.getX(), loc.getY(), loc.getZ(), 2.0f, true);
			Canary.getServer().removeSynchronousTask(this);
		}
		else {
			cow.setFireTicks(600);
			cow.setHealth((float)cow.getMaxHealth());
		}
	}
}
