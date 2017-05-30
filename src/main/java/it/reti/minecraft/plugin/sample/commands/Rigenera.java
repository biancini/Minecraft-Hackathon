package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.Command;
import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

@Command(aliases = { "rigenera" },
	description = "Rigenera salute per il giocatore che lo invoca.",
	registerInEventBus = true,
	registerGameEvent = false)
public class Rigenera extends GenericCommand implements ICommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer me = (EntityPlayer) sender;
		me.setHealth(me.getMaxHealth());
	}
	
}
