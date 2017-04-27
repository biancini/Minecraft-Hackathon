package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.utils.HelperFunctions;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class Mandria implements CommandListener {

	@Command(aliases = { "mandria" },
			description = "Crea una mandria di mucche!",
			permissions = { "" },
			min = 2, // Numero di argomenti del comando
			toolTip = "/mandria <numero di mucche>")
	public void creaMandria(MessageReceiver caller, String[] parameters) {
		if (caller instanceof Player) {
			Player me = (Player) caller;
			Location origin = me.getLocation();
			int numMucche = Integer.parseInt(parameters[1]);

			creaMucche(origin, 10, numMucche);
		}
	}	
	
	private void creaMucche(Location loc, int size, int count) {
		World w = loc.getWorld();
		double x = loc.getX();
		double z = loc.getZ();
		
		for (int i = 0; i < count; i++) {
			Location m = new Location(w, x + (Math.random() * size), 0, z + (Math.random() * size), 0, 0);
			m.setY(w.getHighestBlockAt((int) m.getX(), (int) m.getZ()) + 2);
			HelperFunctions.creaEssereVivente(m, EntityType.COW);
		}
	}
	
}
