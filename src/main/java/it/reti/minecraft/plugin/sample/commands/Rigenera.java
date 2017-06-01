package it.reti.minecraft.plugin.sample.commands;

import it.reti.minecraft.plugin.sample.helpers.GenericCommand;
import it.reti.minecraft.plugin.sample.helpers.MinecraftEvent;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

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
