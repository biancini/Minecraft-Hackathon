package it.reti.minecraft.plugin.sample.commands;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.canarymod.commandsys.CommandListener;

public class Rigenera implements CommandListener {
	
	@Command(aliases = { "rigenera" },
			description = "Rigenera salute e azzera fame per il giocatore che lo invoca.",
			permissions = { "" },
			toolTip = "/rigenera")
	public void creaCasa(MessageReceiver caller, String[] parameters) {
		if (caller instanceof Player) {
			Player me = (Player) caller;
			me.setHunger(20);
			me.setHealth(20);
		}
	}
	
}
