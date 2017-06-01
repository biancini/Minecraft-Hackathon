package it.reti.minecraft.plugin.sample.tasks;

import it.reti.minecraft.plugin.sample.SamplePlugin;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.math.BlockPos;

public class MuccaTask extends EntityAIBase {
	private EntityCow cow = null;
	
	public MuccaTask(EntityCow cow) {
		this.cow = cow;
	}
	
	private int getWorldHeight() {
		for (int i = 0; i < 256; i++){
			BlockPos pos = new BlockPos(cow.getPosition().getX(), i, cow.getPosition().getZ());
			
		    if (Block.getIdFromBlock(cow.getEntityWorld().getBlockState(pos).getBlock()) == 0) {
		        return i;
		    }
		}
		
		return -1;
	}
	
    public void startExecuting() {
		// Verifica se la mucca ha toccato il suolo o meno.
		if (cow.getPosition().getY() == getWorldHeight()) {
			// Se la mucca è a terra uccidila e falla esplodere.
			cow.setHealth(0);
			cow.getEntityWorld().createExplosion(cow, cow.getPosition().getX(), cow.getPosition().getY(), cow.getPosition().getZ(), 2.0f, true);
			
			// Rimuoviamo questo task dal server in modo che non venga nuovamente eseguito.
			SamplePlugin.logger.info("Rimosso un MuccaTask perchè la mucca è esplosa e defunta!");
			cow.tasks.removeTask(this);
		}
		else {
			// Incendia la mucca e aumenta la sua vita al massimo in modo che non muoia per via del fuoco.
			cow.setFire(600);
			cow.setHealth((float)cow.getMaxHealth());
		}
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
}