package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.HelperFunctions;
import it.reti.minecraft.plugin.sample.helpers.Location;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

@Command(aliases = { "mandria" },
	description = "Crea una mandria di mucche!",
	registerInEventBus = true,
	registerGameEvent = false)
public class Mandria extends GenericCommand implements ICommand {
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if (args.length != 1) {
			HelperFunctions.sendErrorMessage(sender, "Invalid number of arguments!");
			return;
		}
		
		EntityPlayer me = (EntityPlayer) sender;
		Location origin = new Location(me);
		int numMucche = Integer.parseInt(args[0]);

		creaMucche(origin, 10, numMucche);
	}	
	
	private void creaMucche(Location loc, int size, int count) {
		World w = loc.getWorld();
		double x = loc.getPosX();
		double z = loc.getPosZ();
		
		
		for (int i = 0; i < count; i++) {
			Location m = new Location(w, x + (Math.random() * size), 0, z + (Math.random() * size));
			m.setPosY(2. + w.getHeight((int) m.getPosX(), (int) m.getPosZ()));
			HelperFunctions.creaEssereVivente(m, EntityCow.class);
		}
	}
	
}
