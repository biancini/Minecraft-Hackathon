package it.reti.minecraft.plugin.sample.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public abstract class GenericCommand extends GenericExtension implements ICommand {
	
	@Override
	public String getName() {
		MinecraftEvent command = this.getClass().getAnnotation(MinecraftEvent.class);
		if (command != null) {
			return command.description();
		}
		return null;
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		String commandalias = (getAliases() != null) ? getAliases().get(0) : "command";
		return "/" + commandalias;
	}

	@Override
	public List<String> getAliases() {
		MinecraftEvent command = this.getClass().getAnnotation(MinecraftEvent.class);
		if (command != null) {
			List<String> aliases = new ArrayList<String>();
			Collections.addAll(aliases, command.aliases());
			return aliases;
		}
		return null;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender instanceof EntityPlayer;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
