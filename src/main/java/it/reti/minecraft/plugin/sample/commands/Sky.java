package it.reti.minecraft.plugin.sample.commands;

import java.util.ArrayList;
import java.util.List;

import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.Location;
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
	registerInEventBus = true,
	registerGameEvent = false)
public class Sky extends GenericCommand implements ICommand {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		List<EntityLiving> list = getLivingEntities(me.getEntityWorld());
		
		for (EntityLiving target : list) {
			Location loc = new Location(target);
			double y = loc.getPosY();
			// Aggiungi 50 alla coordinata y, porta in cielo l'essere vivente di 50 blocchi.
			loc.setPosY(y + 50);
			target.setPositionAndUpdate(loc.getPosX(), loc.getPosY(), loc.getPosZ());
		}
	}
	
	private List<EntityLiving> getLivingEntities(World world) {
			List<EntityLiving> entities = new ArrayList<EntityLiving>();
			for (Entity entity : world.loadedEntityList) {
				if (entity instanceof EntityLiving) {
					entities.add((EntityLiving) entity);
				}
			}
			return entities;
	}
}
