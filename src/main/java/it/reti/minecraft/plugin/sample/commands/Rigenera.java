package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

/**
 * Classe per il comando rigenera che riporta al massimo la vita del giocatore che lo invoca e azzera la sua fame.
 * 
 * La documentazione sulla libreria Forge è navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@MinecraftEvent(aliases = { "rigenera" },
	description = "Rigenera salute e elimina fame per il giocatore che lo invoca.",
	registerInEventBus = true)
public class Rigenera extends GenericCommand implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		
		me.setHealth(me.getMaxHealth());
		me.getFoodStats().setFoodLevel(20);
	}
	
}
