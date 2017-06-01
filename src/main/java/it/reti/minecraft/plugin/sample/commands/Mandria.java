package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@MinecraftEvent(aliases = { "mandria" },
	description = "Crea una mandria di mucche!",
	registerInEventBus = true)
public class Mandria extends GenericCommand implements ICommand {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if (args.length != 1) {
			sendErrorMessage(sender, "Invalid number of arguments!");
			return;
		}
		
		EntityPlayer me = (EntityPlayer) sender;
		BlockPos origin = new BlockPos(me);
		World w = me.getEntityWorld();
		int numMucche = Integer.parseInt(args[0]);

		int size = 10;
		for (int i = 0; i < numMucche; i++) {
			double x = origin.getX() + (Math.random() * size);
			double z = origin.getZ() + (Math.random() * size);
			double y = 2. + w.getHeight((int) x, (int) z); 
			
			BlockPos m = new BlockPos(x, y, z);
			
			creaEssereVivente(w, m, EntityCow.class);
		}
	}	
	
}
