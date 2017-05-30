package it.reti.minecraft.plugin.sample.tasks;

/*
public class MuccaTask extends ServerTask {
	private Cow cow = null;
	
	public MuccaTask(Cow cow) {
		super(Canary.getServer(), 0, true);
		this.cow = cow;
	}
	
	@Override
	public void run() {
		// Verifica se la mucca ha toccato il suolo o meno.
		if (cow.isOnGround()) {
			// Se la mucca è a terra uccidila e falla esplodere.
			Location loc = cow.getLocation();
			cow.setHealth(0);
			cow.kill();
			cow.getWorld().makeExplosion(cow, loc.getX(), loc.getY(), loc.getZ(), 2.0f, true);
			
			// Rimuoviamo questo task dal server in modo che non venga nuovamente eseguito.
			SamplePlugin.logger.info("Rimosso un MuccaTask perchè la mucca è esplosa e defunta!");
			Canary.getServer().removeSynchronousTask(this);
		}
		else {
			// Incendia la mucca e aumenta la sua vita al massimo in modo che non muoia per via del fuoco.
			cow.setFireTicks(600);
			cow.setHealth((float)cow.getMaxHealth());
		}
	}
}
*/