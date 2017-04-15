package it.reti.minecraft.plugin.sample.commands;

import java.util.List;

import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class Sky implements CommandListener {
	@Command(aliases = { "sky" },
			description = "Fa volare tutte le creature viventi del tuo mondo!",
			permissions = { "" },
			toolTip = "/sky")
	public void skyCommand(MessageReceiver caller, String[] parameters) {
		if (caller instanceof Player) {
			Player me = (Player) caller;
			List<EntityLiving> list = me.getWorld().getEntityLivingList();
			
			for (EntityLiving target : list) {
				if (!(target instanceof Player)) {
					Location loc = target.getLocation();
					double y = loc.getY();
					// Aggiungi 50 alla coordinata y, porta in cielo l'essere vivente di 50 blocchi.
					loc.setY(y + 50);
					target.teleportTo(loc);
				}
			}
		}
	}
}
