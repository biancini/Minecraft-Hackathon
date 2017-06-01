package it.reti.minecraft.plugin.sample.tasks;

import it.reti.minecraft.plugin.sample.SamplePlugin;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityCow;

public class MuccaTask extends EntityAIBase {
	private EntityCow cow = null;

	public MuccaTask(EntityCow cow) {
		this.cow = cow;
	}

	public void updateTask() {
		// Verifica se la mucca ha toccato il suolo o meno.
		if (cow.getPosition().getY() == cow.getEntityWorld().getHeight(cow.getPosition()).getY()) {
			// Se la mucca è a terra uccidila e falla esplodere.
			cow.setHealth(0);
			cow.getEntityWorld().createExplosion(cow, cow.getPosition().getX(), cow.getPosition().getY(), cow.getPosition().getZ(), 2.0f, true);

			// Rimuoviamo questo task dal server in modo che non venga
			// nuovamente eseguito.
			SamplePlugin.logger.info("Rimosso un MuccaTask perchè la mucca è esplosa e defunta!");
			cow.tasks.removeTask(this);
		} else {
			// Incendia la mucca e aumenta la sua vita al massimo in modo che
			// non muoia per via del fuoco.
			cow.setFire(600);
			cow.setHealth((float) cow.getMaxHealth());
		}
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
}