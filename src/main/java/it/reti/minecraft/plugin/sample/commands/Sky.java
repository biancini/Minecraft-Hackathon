package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

@Command(aliases = { "sky" },
	description = "Fa volare tutte le creature viventi del tuo mondo!",
	registerInEventBus = true)
public class Sky extends GenericCommand implements ICommand {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		World w = me.getEntityWorld();
		
		for (Entity entity : w.loadedEntityList) {
			if (entity instanceof EntityLiving) {
				double x = entity.getPosition().getX();
				double z = entity.getPosition().getZ();
				
				// Aggiungi 50 alla coordinata y, porta in cielo l'essere vivente di 50 blocchi.
				double y = entity.getPosition().getY() + 50;
				
				entity.setPositionAndUpdate(x, y, z);
			}
		}
	}
	
}
