package it.reti.minecraft.plugin.sample.tasks;

import it.reti.minecraft.plugin.sample.SamplePlugin;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityCow;

public class MuccaTask extends EntityAIBase {
	private EntityCow cow = null;
	private boolean executing = false;

	public MuccaTask(EntityCow cow) {
		this.cow = cow;
		this.executing = true;
	}

	public void updateTask() {
		if (cow == null) return;
		
		// Verifica se la mucca ha toccato il suolo o meno.
		if (cow.getPosition().getY() == cow.getEntityWorld().getHeight(cow.getPosition()).getY()) {
			// Uccidi la mucca e falla esplodere.
			int x = cow.getPosition().getX();
			int y = cow.getPosition().getY();
			int z = cow.getPosition().getZ();
			cow.getEntityWorld().createExplosion(null, x, y, z, 2.0f, true);
			cow.setHealth(0);
			
			// Rimuoviamo questo task dal server in modo che non venga
			// nuovamente eseguito.
			SamplePlugin.logger.info("Rimosso un MuccaTask perchè la mucca è esplosa e defunta!");
			this.executing = false;
		} else {
			// Incendia la mucca e aumenta la sua vita al massimo in modo che
			// non muoia per via del fuoco.
			cow.setFire(600);
			cow.setHealth(cow.getMaxHealth());
		}
	}

	@Override
	public boolean shouldExecute() {
		return executing;
	}
}